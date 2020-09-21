package com.jeffrey.context.properties;

import lombok.Data;

@Data
public class AliyunOSSProperties {

    /**
     * 上传域名
     */
    private String endpoint;

    /**
     * accesskey
     */
    private String accessKeyId;

    /**
     * accesskeysecret
     */
    private String accessKeySecret;

    /**
     * 上传的bucket名称
     */
    private String bucketName;

    /**
     * 上传文件名
     */
    private String folder;

    /**
     * 上传文件最大值
     */
    private long maxFileSize;

    /**
     * 自定义域名
     */
    private String customHost;

    private String region;

    private String roleArn;

    private String policy;

    private String version;

    private String host;

    private long durationSeconds;

    private String isPrivate;
}
