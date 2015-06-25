package com.jd.jr.simpleconfig.export;


import com.google.gson.reflect.TypeToken;
import com.jd.jr.simpleconfig.export.DTO.DataConfigDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不断轮询配置中心服务接口
 * Created by yangkuan on 15/6/23.
 */
public class DataConfigResource {
    private final static Logger log = LoggerFactory.getLogger(DataConfigResource.class);
    @Resource(name = "dataConfigExport")
    private DataConfigExport dataConfigExport;

    String systemName;//系统编码

    Long refreshTime = 2000l;//轮询时间默认是2秒


    String backupPath = System.getProperty("user.home") + "/dataconfig/backup/";

    public Map<String, DataConfigDTO> dataConfigMap = new HashMap<String,DataConfigDTO>();
    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    try {
                        createFolder(new String[]{backupPath});
                        Thread.sleep(refreshTime);
                        List<DataConfigDTO> dataConfigList = dataConfigExport.queryBySystemName(systemName);
                        Map<String, DataConfigDTO> dataKeyMap1 = new HashMap<String,DataConfigDTO>();
                        for (DataConfigDTO dataConfig : dataConfigList) {
                            dataKeyMap1.put(dataConfig.getDataKey(),dataConfig);
                        }
                        dataConfigMap = dataKeyMap1;
                        String dataConfigJsonString = GsonUtils.toJson(dataConfigList);
                        if(dataConfigJsonString!=null&&!dataConfigJsonString.equals("")){
                           String result = readFile(getFilePath(backupPath, systemName));
                            if(result==null||result.equals("")){
                                writeFile(getFilePath(backupPath, systemName),dataConfigJsonString);

                            } else if(!result.equals(dataConfigJsonString)){
                                writeFile(getFilePath(backupPath, systemName),dataConfigJsonString);
                            }
                        }
                    } catch (Exception e) {
                        log.error("请求配置中心服务异常:", e);
                        String result = readFile(getFilePath(backupPath, systemName));
                        if(result!=null){
                            List<DataConfigDTO> dataConfigList = GsonUtils.fromJson(result,new TypeToken<List<DataConfigDTO>>(){});
                            Map<String, DataConfigDTO> dataKeyMap1 = new HashMap<String,DataConfigDTO>();
                            for (DataConfigDTO dataConfig : dataConfigList) {
                                dataKeyMap1.put(dataConfig.getDataKey(),dataConfig);
                            }
                            dataConfigMap = dataKeyMap1;
                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    public void reload() {
        this.init();
    }

    public   void writeFile(String path, String content) {
        BufferedWriter writer = null;
        try {
            File file = new File(path);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            // log.info("writeFile(" + path + ") success.");
        } catch (Exception e) {
            log.error("writeFile(" + path + ") error.", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            } catch (Exception e) {
                log.error("WriteFile(" + path + ") close error.", e);
            }
        }
    }

    public   String readFile(String path) {
        String result = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                result = convertStreamToString(new FileInputStream(file));
               // log.info("readFile(" + path + ") success.");
            }
        } catch (Exception e) {
            log.error("readFile(" + path + ") error.", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private String getFilePath(String path, String systemName) {
        return path + systemName + ".config";
    }

    public static String convertStreamToString(InputStream is) {
        if (is == null) {
            return null;
        }
        int size = 4096;
        String result = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            byte[] b = new byte[size];
            int len = 0;

            while ((len = is.read(b, 0, size)) != -1) {
                out.write(b, 0, len);
            }

            out.flush();
            result = new String(out.toByteArray(), Charset.forName("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
                out.close();
                is = null;
                out = null;
            } catch (Exception ex) {
                log.error("将本地文件流转换为字符串结束时发生异常",ex);
            }
        }
        return result;
    }

    public static void createFolder(String[] paths) {
        try {
            for (String path : paths) {
                File file = new File(path);
                if (!(file.exists())) {
                    Boolean mkdirFlag = file.mkdir();
                    if(mkdirFlag)
                      log.info("createFolder(" + path + ") success.");
                }
            }
        } catch (Exception e) {
            log.error("createFolder(" + paths[0] + ") etc error.", e);
        }
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public static void main(String[] args){
        DataConfigResource dataConfigResource = new DataConfigResource();
        dataConfigResource.createFolder(new String[]{System.getProperty("user.home")+"/dataconfig/backup"});
    }
}
