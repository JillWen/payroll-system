package com.bamboocloud.homework.pay.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>文件名称: FileUtil </p>
 * <p>文件描述:   </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/17
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static FileUtil fileUtil = new FileUtil();

    private FileUtil() {
    }

    public static FileUtil getInstance(){
        return fileUtil;
    }

    public static String uploadFile(HttpServletRequest request){
        // 实例化一个文件解析器
        CommonsMultipartResolver coMultipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 判断request请求中是否有文件上传
        if (coMultipartResolver.isMultipart(request)) {
            long now = System.currentTimeMillis();
            // 转换request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获得文件
            MultipartFile file = multiRequest.getFile("file");
            if (file != null && !file.isEmpty()) {
                // 获得原始文件名
                String fileName = file.getOriginalFilename();
                String newFileName = System.getProperty("user.dir")  + File.separator + now + String.valueOf(fileName);
                File tempFile = new File(newFileName);
                try {
                    file.transferTo(tempFile);
                    LOGGER.info("file transfer success, file name : {}",newFileName);
                }  catch (Exception e) {
                    LOGGER.error("file transfer failed : {}",e.getMessage());
                }
                return newFileName;
            }
        }
        return null;
    }
}
