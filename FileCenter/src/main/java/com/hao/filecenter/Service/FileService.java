package com.hao.filecenter.Service;

import com.hao.filecenter.Model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    FileInfo upload(MultipartFile file) throws Exception;

    /**
     * 删除文件
     *
     * @param fileInfo
     */
    void delete(FileInfo fileInfo);

}
