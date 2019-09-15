package com.hao.filecenter.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hao.filecenter.Model.FileInfo;
import com.hao.filecenter.Model.FileSource;
import com.hao.filecenter.Utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public abstract class FileService {

    public FileInfo upload(MultipartFile file) throws Exception {
        FileInfo uploadFile = FileUtil.getFileInfo(file);
        // 先根据文件md5查询记录
        FileInfo oldFileInfo = new FileInfo().selectOne(new QueryWrapper<FileInfo>().eq("id",uploadFile.getId()));

        if (oldFileInfo != null) {// 如果已存在文件，避免重复上传同一个文件
            return oldFileInfo;
        }

        if (!uploadFile.getName().contains(".")) {
            throw new IllegalArgumentException("缺少后缀名");
        }

        uploadFile(file, uploadFile);

        uploadFile.setSource(fileSource().name());// 设置文件来源
        uploadFile.insert();

        log.info("上传文件：{}", uploadFile);
        return uploadFile;
    }

    /**
     * 文件来源
     *
     * @return
     */
    protected abstract FileSource fileSource();

    /**
     * 上传文件
     *
     * @param file
     * @param fileInfo
     */
    protected abstract void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception;

    public boolean delete(FileInfo fileInfo){
        return deleteFile(fileInfo);
    }

    /**
     * 删除文件资源
     *
     * @param fileInfo
     * @return
     */
    protected abstract boolean deleteFile(FileInfo fileInfo);

}
