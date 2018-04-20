package com.libarymanagement.console.common;

import com.libarymanagement.core.responseModel.base.JsonResult;
import com.libarymanagement.core.responseModel.base.JsonResultData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @RequestMapping(value = "uploadPic",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadPic(String fileName, HttpServletRequest request) throws IOException {
        //把request转换成复杂类型的request
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        //过得上传的文件
        MultipartFile mf = mr.getFile(fileName);
        //获得字节数组
        byte[] bfile = mf.getBytes();
        //获得当前时间的最小精度
        String tfileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            tfileName = tfileName + random.nextInt(10);
        }

        //获得后缀名
        String oriFileName = mf.getOriginalFilename();
        String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));

        //文件的绝对路径
        String realPath = "http://localhost:8081"+"/image/"+tfileName + suffix;
        String relativePath = "/image/"+tfileName + suffix;

        //创建Jersey客户端
        Client client = Client.create();
        //指定上传资源的位置
        WebResource wr = client.resource(realPath);
        //文件上传
        wr.put(bfile);

//        mf.transferTo(new File(realPath));
        System.out.println("upload ok");
        return new JsonResultData<>(relativePath);
    }
}
