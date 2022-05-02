package com.dbia.archive.page;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

/**
 * HDFS配置类
 * @author linhaiy
 * @date 2019.05.18
 */
public class HdfsConfig {
    @Value("${hdfs.path}")
    private String path;

    @Value("${app.hdfs.username}")
    private String username;

    private FileSystem hdfs;
    public FileSystem getFileSystem(){
        if (hdfs==null){
            Configuration conf = new Configuration();
            conf.addResource(new Path("hdfs-site.xml"));
            conf.setBoolean("fs.hdfs.impl.disable.cache", true);
            try {
                hdfs = FileSystem.get(new URI("hdfs://jq1:9000/user/hadoop"),conf);
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
        return hdfs;
    }
}
