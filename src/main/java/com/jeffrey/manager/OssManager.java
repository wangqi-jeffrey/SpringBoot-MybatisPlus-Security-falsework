package com.jeffrey.manager;


import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jeffrey.context.properties.AliyunOSSProperties;
import com.jeffrey.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云OSS
 */
@Slf4j
public class OssManager extends OSSClient {

    private AliyunOSSProperties oSSConfig;

    public OssManager(AliyunOSSProperties oSSConfig) {
        super(oSSConfig.getEndpoint(), new DefaultCredentialProvider(oSSConfig.getAccessKeyId(), oSSConfig.getAccessKeySecret()), new OSSClientConfiguration());
        this.oSSConfig = oSSConfig;
    }

    public String putObjectInputStream(String folder, String fileName, InputStream input) {
        String key = oSSConfig.getFolder() + "/" + folder + "/" + DateUtil.format("yyyyMMdd", new Date()) + "/" + fileName;
        putObject(oSSConfig.getBucketName(), key, input, null);
        return key;
    }

    public String putFile(String folder, String fileName, File file) {
        String key = oSSConfig.getFolder() + "/" + folder + "/" + DateUtil.format("yyyyMMdd", new Date()) + "/" + fileName;
        putObject(oSSConfig.getBucketName(), key, file);
        return generateUrl(key);
    }

    public String generateUrl(String key) {
        if ("true".equals(oSSConfig.getIsPrivate())) {
            // 返回值地址用自定义的host
            String signedUrl = generatePresignedUrl(key);
            return signedUrl.replace(oSSConfig.getHost(), oSSConfig.getCustomHost());
        }
        return oSSConfig.getCustomHost() + key;
    }

    /**
     * 私有bucket，根据文件路径获取签名url
     *
     * @param key
     * @return
     */
    private String generatePresignedUrl(String key) {
        return generatePresignedUrl(key, DateUtil.addSecond(new Date(), (int) oSSConfig.getDurationSeconds()));
    }

    /**
     * 私有bucket,根据文件路径和过期时间获取签名url
     *
     * @param key
     * @param expiration
     * @return
     */
    private String generatePresignedUrl(String key, Date expiration) {
        return generatePresignedUrl(oSSConfig.getBucketName(), key, expiration, HttpMethod.GET).toString();
    }


    public Map<String, Object> getAuth(String roleSessionName,
                                       String region,
                                       String accessKeyId,
                                       String accessKeySecret,
                                       String roleArn,
                                       String policy,
                                       String version,
                                       long durationSeconds,
                                       String endpoint,
                                       String bucket,
                                       String uploadDir,
                                       String host,
                                       String customHost) {
        log.info("roleSessionName is {}", roleSessionName);
        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;
        Map<String, Object> retJo = new HashMap<>();
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(version);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            if (StringUtils.isNotEmpty(policy))
                request.setPolicy(policy); // AssumeRole时可以指定授权(Policy)，也可以不指定
            request.setDurationSeconds(durationSeconds);

            // 发起请求，并得到response
            final AssumeRoleResponse stsResponse = client.getAcsResponse(request);

            retJo.put("StatusCode", "200");
            retJo.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            retJo.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            retJo.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            retJo.put("Expiration", stsResponse.getCredentials().getExpiration());
            retJo.put("Endpoint", endpoint);
            retJo.put("Bucket", bucket);
            retJo.put("UploadDir", uploadDir + "/pics/" + DateUtil.format("yyyyMMdd", new Date()) + "/");
            retJo.put("Host", host);
            retJo.put("CustomHost", customHost);

        } catch (ClientException e) {
            retJo = new HashMap<>();
            retJo.put("StatusCode", "500");
            retJo.put("ErrorCode", e.getErrCode());
            retJo.put("ErrorMessage", e.getErrMsg());
        }
        return retJo;
    }

    public Object getDefaultAuth(String managerCode) {
        return getAuth(managerCode, oSSConfig.getRegion(),
                oSSConfig.getAccessKeyId(), oSSConfig.getAccessKeySecret(),
                oSSConfig.getRoleArn(), oSSConfig.getPolicy(), oSSConfig.getVersion(), oSSConfig.getDurationSeconds(),
                oSSConfig.getEndpoint(), oSSConfig.getBucketName(), oSSConfig.getFolder(),
                oSSConfig.getHost(), oSSConfig.getCustomHost());
    }
}

class OSSClientConfiguration extends ClientConfiguration {
    public OSSClientConfiguration() {
        super.setProtocol(Protocol.HTTPS);
    }
}
