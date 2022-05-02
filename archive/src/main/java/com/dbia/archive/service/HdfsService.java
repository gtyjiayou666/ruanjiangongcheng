package com.dbia.archive.service;

import com.dbia.archive.page.HdfsConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsService {

    public static boolean mkdir(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (existFile(path)) {
            return true;
        }

        HdfsConfig hdfsConfig = new HdfsConfig();
        FileSystem fs = hdfsConfig.getFileSystem();
        Path srcPath = new Path(path);
        boolean isOk = fs.mkdirs(srcPath);
        fs.close();
        return isOk;
    }

    public static boolean existFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        System.out.print(path);
        HdfsConfig hdfsConfig = new HdfsConfig();
        FileSystem fs = hdfsConfig.getFileSystem();
        Path srcPath = new Path(path);
        boolean isExists = fs.exists(srcPath);
        return isExists;
    }


    public static boolean renameFile(String oldName, String newName) throws Exception {
        if (StringUtils.isEmpty(oldName) || StringUtils.isEmpty(newName)) {
            return false;
        }
        HdfsConfig hdfsConfig = new HdfsConfig();
        FileSystem fs = hdfsConfig.getFileSystem();
        Path oldPath = new Path(oldName);
        Path newPath = new Path(newName);
        boolean isOk = fs.rename(oldPath, newPath);
        fs.close();
        return isOk;
    }


}