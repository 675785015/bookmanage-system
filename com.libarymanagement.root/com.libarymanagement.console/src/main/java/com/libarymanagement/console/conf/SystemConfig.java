package com.libarymanagement.console.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Lee on 2018/4/22.
 */
@Configuration
@ConfigurationProperties(prefix = "system", ignoreUnknownFields = false)
@PropertySource("classpath:system.properties")
public class SystemConfig {
    private String uploadFilesUrl;
    private String uploadPicUrl;

    public String getUploadPicUrl() {
        return uploadPicUrl;
    }

    public void setUploadPicUrl(String uploadPicUrl) {
        this.uploadPicUrl = uploadPicUrl;
    }

    public String getUploadFilesUrl() {

        return uploadFilesUrl;
    }

    public void setUploadFilesUrl(String uploadFilesUrl) {
        this.uploadFilesUrl = uploadFilesUrl;
    }
}
