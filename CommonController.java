package com.wms.controller;

import com.wms.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@RestController
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    private final static String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/";


    @PostMapping("/common/upload")
    public Result uploadFile(MultipartFile file) {
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String randomUUID = UUID.randomUUID().toString();
        String fileName = randomUUID + suffix;

        File dir = new File(FILE_UPLOAD_PATH);

        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(!mkdirs){
                return Result.fail();
            }
        }

        try {
            file.transferTo(new File(FILE_UPLOAD_PATH + fileName));
            return Result.suc("http://localhost:8090/upload/" + fileName);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.fail();
        }
    }
}
