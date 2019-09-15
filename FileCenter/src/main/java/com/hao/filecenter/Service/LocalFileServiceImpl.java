package com.hao.filecenter.Service;

import com.hao.filecenter.Model.FileInfo;
import com.hao.filecenter.Model.FileSource;
import com.hao.filecenter.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * 本地存储文件<br>
 * 该实现文件服务只能部署一台<br>
 * 如多台机器间能共享到一个目录，即可部署多台
 */
@Service("localFileServiceImpl")
public class LocalFileServiceImpl extends FileService {

    @Value("${file.local.urlPrefix}")
    private String urlPrefix;

    // 上传文件存储在本地的根路径
    @Value("${file.local.path}")
    private String localFilePath;

    @Override
    protected FileSource fileSource() {
        return FileSource.LOCAL;
    }

    protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
        int index = fileInfo.getName().lastIndexOf(".");
        // 文件扩展名
        String fileSuffix = fileInfo.getName().substring(index);

        String suffix = "/" + LocalDate.now().toString().replace("-", "/") + "/" + fileInfo.getId() + fileSuffix;

        String path = localFilePath + suffix;
        String url = urlPrefix + suffix;
        fileInfo.setPath(path);
        fileInfo.setUrl(url);
        FileUtil.saveFile(file, path);
    }

    @Override
    protected boolean deleteFile(FileInfo fileInfo) {
        boolean isDelete = fileInfo.deleteById();
        if(isDelete){
            return FileUtil.deleteFile(fileInfo.getPath());
        }
        return false;
    }
}
