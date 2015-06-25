package com.jd.jr.simpleconfig.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;

/**
 * User: yangkuan@jd.com
 * Date: 14-11-25
 * Time: 上午10:59
 */
public class VelocityTemplate {
    /*
	 * 生成velocity模版
	 */
    public static <T> String renderVelocityTemplate(T params, String velocityName) {
        BufferedReader reader = null;
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(velocityName);
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            return evaluate(reader, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }
        return null;
    }

    private static <T> String evaluate(Reader reader, T variables) throws IOException {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("Obj", variables);
        StringWriter writer = new StringWriter();
        Velocity.evaluate(velocityContext, writer, "", reader);
        return writer.toString();
    }
}
