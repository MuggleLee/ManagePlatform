package com.hao.filecenter.service;

import com.aliyun.oss.OSSClient;
import com.hao.filecenter.model.FileInfo;
import com.hao.filecenter.model.FileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云存储文件
 */
@Service("aliyunFileServiceImpl")
public class AliyunFileServiceImpl extends FileService {


    @Override
    protected FileSource fileSource() {
        return FileSource.ALIYUN;
    }

    @Autowired
    private OSSClient ossClient;

    @Value("${file.aliyun.bucketName}")
    private String bucketName;

    @Value("${file.aliyun.domain}")
    private String domain;

    @Override
    protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
        ossClient.putObject(bucketName, fileInfo.getName(), file.getInputStream());
        fileInfo.setUrl(domain + "/" + fileInfo.getName());
    }

    @Override
    protected boolean deleteFile(FileInfo fileInfo) {
        ossClient.deleteObject(bucketName, fileInfo.getName());
        return true;
    }

}
