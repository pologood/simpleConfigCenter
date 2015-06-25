package com.jd.jr.simpleconfig.util;

import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * User: yangkuan@jd.com
 * Date: 14-7-21
 * Time: 下午3:08
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GsonJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
    private static final Logger log = Logger.getLogger(GsonJsonProvider.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_CHARSET = "UTF-8";

    public GsonJsonProvider() {
    }

    @Override
    public long getSize(Object arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Object.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
            WebApplicationException {
        String charset = getCharset(mediaType);

        String json = GsonUtils.toJson(object, genericType, DEFAULT_DATE_FORMAT);

        httpHeaders.add("Content-Type","application/json");//增加请求类型
        entityStream.write(json.getBytes(charset));
        entityStream.flush();
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // genericType.getClass().
        // return type.equals(Object.class);
        return Object.class.isAssignableFrom(type);

    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException,
            WebApplicationException {
        String charset = getCharset(mediaType);
        BufferedReader reader = new BufferedReader(new InputStreamReader(entityStream, charset));

        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String json = builder.toString();

        return GsonUtils.fromJson(json, genericType, DEFAULT_DATE_FORMAT);
    }

    /**
     * 获取编码
     *
     * @param mediaType
     * @return
     */
    public static String getCharset(MediaType mediaType) {
        if (mediaType != null) {
            String charset = mediaType.getParameters().get("charset");
            if (charset != null)
                return charset;
        }
        return DEFAULT_CHARSET;
    }

}
