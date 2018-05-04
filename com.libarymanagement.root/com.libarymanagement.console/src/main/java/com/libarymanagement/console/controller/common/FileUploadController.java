package com.libarymanagement.console.controller.common;

import com.libarymanagement.console.conf.SystemConfig;
import com.libarymanagement.core.responseModel.base.JsonResult;
import com.libarymanagement.core.responseModel.base.JsonResultData;
import com.libarymanagement.core.responseModel.base.JsonResultError;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Lee on 2018/4/18.
 */
@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

    @Autowired
    private SystemConfig systemConfig;

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

    @RequestMapping(value = "uploadPic",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadPic(String fileName, HttpServletRequest request) throws IOException {
        //把request转换成复杂类型的request
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        //过得上传的文件
        MultipartFile mf = mr.getFile(fileName);

        //获得后缀名
        String oriFileName = mf.getOriginalFilename();

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(oriFileName, type)) {
                isLegal = true;
                break;
            }
        }

        if(!isLegal){
            return new JsonResultError("文件格式不正确");
        }
        String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));

        //获得字节数组
        byte[] bfile = mf.getBytes();
        //获得当前时间的最小精度
        String tfileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            tfileName = tfileName + random.nextInt(10);
        }

        //文件的绝对路径
        String realPath = systemConfig.getUploadFilesUrl()+tfileName + suffix;
        String relativePath = "/image/"+tfileName + suffix;
        //创建Jersey客户端
        Client client = Client.create();
        //指定上传资源的位置
        WebResource wr = client.resource(realPath);
        //文件上传
        wr.put(bfile);

        return new JsonResultData<>(relativePath);
    }
}
