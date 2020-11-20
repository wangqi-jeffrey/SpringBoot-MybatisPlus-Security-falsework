### 项目介绍
SpringBoot+MybatisPlus+Security 项目脚手架

### 运行环境
* jdk: 11+
* gradle: 6.4.+ https://docs.gradle.org/current/userguide/userguide.html
* spring-boot: 2.4.0-SNAPSHOT https://spring.io/
* swagger2: 2.9.0 https://swagger.io/
* mybatis plus: 3.4.0 https://baomidou.com/

### 帮助
* keystore生成：
```
keytool -genkey -validity 36000 -alias xxxx.com -keyalg RSA -keystore ~/jwt-staging2.keystore
```

### 项目结构
```
.
├── 1.txt
├── Dockerfile
├── README.md
├── build
│   ├── classes
│   │   └── java
│   │       └── main
│   │           ├── META-INF
│   │           │   └── spring-configuration-metadata.json
│   │           └── com
│   │               └── jeffrey
│   │                   ├── Application.class
│   │                   ├── aspect
│   │                   │   ├── BaseArgsAspect.class
│   │                   │   ├── CacheAspect$CacheParameterizedType.class
│   │                   │   ├── CacheAspect.class
│   │                   │   ├── HandlerMonitorAspect.class
│   │                   │   ├── LockAspect.class
│   │                   │   └── ParamValidationAspect.class
│   │                   ├── context
│   │                   │   ├── HttpServletRequestContextHolder.class
│   │                   │   ├── annotation
│   │                   │   │   ├── Cache.class
│   │                   │   │   ├── EnumField.class
│   │                   │   │   ├── Lock.class
│   │                   │   │   ├── MoreThan.class
│   │                   │   │   ├── ScriptValid$List.class
│   │                   │   │   ├── ScriptValid.class
│   │                   │   │   └── VerifyObject.class
│   │                   │   ├── common
│   │                   │   │   ├── Builder$Consumer1.class
│   │                   │   │   ├── Builder$Consumer2.class
│   │                   │   │   ├── Builder$Consumer3.class
│   │                   │   │   ├── Builder.class
│   │                   │   │   ├── Cacheable.class
│   │                   │   │   └── Lockable.class
│   │                   │   ├── configuration
│   │                   │   │   ├── BeanConfiguration.class
│   │                   │   │   ├── InitCommandLineRunner.class
│   │                   │   │   ├── MQConfiguration.class
│   │                   │   │   ├── RedisConfiguration.class
│   │                   │   │   ├── SwaggerConfiguration.class
│   │                   │   │   ├── ThreadPoolClient.class
│   │                   │   │   ├── WxMaConfiguration.class
│   │                   │   │   └── XxlJobConfig.class
│   │                   │   ├── constant
│   │                   │   │   ├── CCBConstant.class
│   │                   │   │   ├── CommonConstant.class
│   │                   │   │   ├── ErrorMsgConstant.class
│   │                   │   │   └── UserConstant.class
│   │                   │   ├── enums
│   │                   │   │   ├── BillFlowPayTypeEnum.class
│   │                   │   │   ├── BillFlowTradingChannelEnum.class
│   │                   │   │   ├── BillSourceEnum.class
│   │                   │   │   ├── BillStatusEnum.class
│   │                   │   │   ├── BillTypeDictEnum.class
│   │                   │   │   ├── BusinessCodeEnum.class
│   │                   │   │   ├── CCBAreaEnum.class
│   │                   │   │   ├── CCBBillPayStatusEnum.class
│   │                   │   │   ├── CCBContractSigningStatusEnum.class
│   │                   │   │   ├── CCBCredentialsTypeEnum.class
│   │                   │   │   ├── CCBFocusRoomRentStatusEnum.class
│   │                   │   │   ├── CCBProjectTypeEnum.class
│   │                   │   │   ├── CCBSyncStatusEnum.class
│   │                   │   │   ├── CCBTransCodeEnum.class
│   │                   │   │   ├── CalculationFieldEnum.class
│   │                   │   │   ├── CalculationModeEnum.class
│   │                   │   │   ├── CashierTypeEnum.class
│   │                   │   │   ├── DeleteStatusEnum.class
│   │                   │   │   ├── EnumInterface.class
│   │                   │   │   ├── ErrorCodeEnum.class
│   │                   │   │   ├── HzfPayTypeEnum.class
│   │                   │   │   ├── MeterReadingSourceEnum.class
│   │                   │   │   ├── OperateLogTypeEnum$Module.class
│   │                   │   │   ├── OperateLogTypeEnum.class
│   │                   │   │   ├── PicTypeEnum.class
│   │                   │   │   ├── PlatformEnum.class
│   │                   │   │   ├── PriceConfigEnum.class
│   │                   │   │   ├── PriceDictConfigEnum.class
│   │                   │   │   ├── RentContractConfirmStatusEnum.class
│   │                   │   │   ├── RentContractSigningStatusEnum.class
│   │                   │   │   ├── RentContractSigningTypeEnum.class
│   │                   │   │   ├── RentStatusEnum.class
│   │                   │   │   └── UserRoleEnum.class
│   │                   │   ├── exception
│   │                   │   │   ├── BaseException.class
│   │                   │   │   ├── JwtAuthenticationException.class
│   │                   │   │   ├── LackParameterException.class
│   │                   │   │   └── LockException.class
│   │                   │   ├── mybatis
│   │                   │   │   ├── MyMetaObjectHandler.class
│   │                   │   │   └── MybatisPlusConfig.class
│   │                   │   ├── properties
│   │                   │   │   ├── AliyunOSSProperties.class
│   │                   │   │   ├── JXSaasProperties.class
│   │                   │   │   ├── JwtTokenProperties.class
│   │                   │   │   ├── SmsProperties.class
│   │                   │   │   ├── ThreadPoolProperties.class
│   │                   │   │   ├── WxMaProperties$Config.class
│   │                   │   │   ├── WxMaProperties.class
│   │                   │   │   └── WxMpProperties.class
│   │                   │   └── security
│   │                   │       ├── AuthenticationContextHolder.class
│   │                   │       ├── Authorityable.class
│   │                   │       ├── JwtAuthenticationFilter.class
│   │                   │       ├── MySimpleGrantedAuthority.class
│   │                   │       ├── UserAuthenticationFailureHandler.class
│   │                   │       ├── UserTokenAuthenticationProvider.class
│   │                   │       ├── UserTokenAuthenticationToken.class
│   │                   │       └── WebSecurityConfig.class
│   │                   ├── controller
│   │                   │   └── UserController.class
│   │                   ├── dto
│   │                   │   ├── ManagerAccountDTO.class
│   │                   │   ├── UserInfoDTO.class
│   │                   │   ├── request
│   │                   │   │   ├── BaseListRequestDTO.class
│   │                   │   │   ├── BaseRequestDTO.class
│   │                   │   │   └── user
│   │                   │   │       ├── ManagerAccountRequestDTO.class
│   │                   │   │       └── UserLoginRequestDTO.class
│   │                   │   └── response
│   │                   │       ├── ResponseDTO.class
│   │                   │       ├── ResponseStatus.class
│   │                   │       └── user
│   │                   │           ├── LandlordInfoResponseDTO.class
│   │                   │           ├── ManagerAccountResponseDTO.class
│   │                   │           └── UserResponseDTO.class
│   │                   ├── entity
│   │                   │   ├── BaseEntity.class
│   │                   │   ├── OperateLog.class
│   │                   │   └── User.class
│   │                   ├── handler
│   │                   │   └── demo
│   │                   │       ├── CommandJobHandler.class
│   │                   │       ├── DemoJobHandler.class
│   │                   │       ├── HttpJobHandler.class
│   │                   │       └── ShardingJobHandler.class
│   │                   ├── manager
│   │                   │   ├── AuthenticationManager.class
│   │                   │   ├── BusinessCodeManager.class
│   │                   │   ├── CacheManager.class
│   │                   │   ├── CdocManager.class
│   │                   │   ├── LockManager.class
│   │                   │   ├── OSSClientConfiguration.class
│   │                   │   ├── OperateLogManager.class
│   │                   │   ├── OssManager.class
│   │                   │   ├── QiWechatManager.class
│   │                   │   ├── SmsManager$ErrCode.class
│   │                   │   ├── SmsManager.class
│   │                   │   ├── SpringContrextUtils.class
│   │                   │   ├── WxMaManager.class
│   │                   │   └── mq
│   │                   │       ├── consumer
│   │                   │       │   ├── MQConsumer.class
│   │                   │       │   └── RedisMQConsumer.class
│   │                   │       └── producer
│   │                   │           ├── MQProducer.class
│   │                   │           └── RedisMQProducer.class
│   │                   ├── mapper
│   │                   │   └── user
│   │                   │       ├── OperateLogMapper.class
│   │                   │       └── UserMapper.class
│   │                   ├── plugin
│   │                   │   ├── CodeGenerator$1.class
│   │                   │   ├── CodeGenerator$2.class
│   │                   │   └── CodeGenerator.class
│   │                   ├── receiver
│   │                   │   ├── AbstractReceiver.class
│   │                   │   └── log
│   │                   │       └── OperateLogReceiver.class
│   │                   ├── service
│   │                   │   ├── UserService.class
│   │                   │   └── impl
│   │                   │       └── UserServiceImpl.class
│   │                   ├── utils
│   │                   │   ├── AnalyzeUtil$Analyzable.class
│   │                   │   ├── AnalyzeUtil$Triple.class
│   │                   │   ├── AnalyzeUtil.class
│   │                   │   ├── Assert.class
│   │                   │   ├── BeanCopyUtils.class
│   │                   │   ├── BeanMapUtils.class
│   │                   │   ├── BeanUtil$1.class
│   │                   │   ├── BeanUtil.class
│   │                   │   ├── DateUtil.class
│   │                   │   ├── EnumUtil.class
│   │                   │   ├── FreemarkerUtil.class
│   │                   │   ├── HttpUtil.class
│   │                   │   ├── JAXBTool.class
│   │                   │   ├── JpushUtil.class
│   │                   │   ├── JwtTokenUtil.class
│   │                   │   ├── LogUtil.class
│   │                   │   ├── MoneyUtil.class
│   │                   │   ├── NetTool.class
│   │                   │   ├── NetUtil.class
│   │                   │   ├── NumberToCN.class
│   │                   │   ├── RegexUtil.class
│   │                   │   ├── RequestUtil.class
│   │                   │   ├── ResponseUtil.class
│   │                   │   ├── StringUtil.class
│   │                   │   ├── XMathUtil.class
│   │                   │   └── json
│   │                   │       ├── GsonTypeAdapter$1.class
│   │                   │       ├── GsonTypeAdapter.class
│   │                   │       ├── GsonUtil$1.class
│   │                   │       ├── GsonUtil$2.class
│   │                   │       └── GsonUtil.class
│   │                   └── vo
│   │                       ├── payment
│   │                       │   ├── CashierParamVO.class
│   │                       │   ├── PayNotifyVO.class
│   │                       │   └── SyncUserInfoParamVo.class
│   │                       ├── user
│   │                       │   ├── OperateLogVO.class
│   │                       │   └── WxUserInfoVO.class
│   │                       └── wechat
│   │                           └── CollectionMsgVO.class
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       └── main
│   │       └── headers
│   │           └── java
│   │               └── main
│   ├── resources
│   │   └── main
│   │       ├── application-local.yml
│   │       ├── application.yml
│   │       ├── keystore
│   │       │   ├── jwt-dev.keystore
│   │       │   ├── jwt-pre.keystore
│   │       │   ├── jwt-prod.keystore
│   │       │   └── jwt-staging2.keystore
│   │       ├── logback.xml
│   │       ├── mapper
│   │       │   ├── OperateLogMapper.xml
│   │       │   └── UserMapper.xml
│   │       ├── static
│   │       │   ├── pay-result.html
│   │       │   ├── pay-service.html
│   │       │   ├── privacy-policy.html
│   │       │   └── user-protocol.html
│   │       └── templates
│   │           └── rent-contract.ftl
│   └── tmp
│       └── compileJava
│           └── source-classes-mapping.txt
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── init.sql
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── jeffrey
    │   │           ├── Application.java
    │   │           ├── aspect
    │   │           │   ├── BaseArgsAspect.java
    │   │           │   ├── CacheAspect.java
    │   │           │   ├── HandlerMonitorAspect.java
    │   │           │   ├── LockAspect.java
    │   │           │   └── ParamValidationAspect.java
    │   │           ├── context
    │   │           │   ├── HttpServletRequestContextHolder.java
    │   │           │   ├── annotation
    │   │           │   │   ├── Cache.java
    │   │           │   │   ├── EnumField.java
    │   │           │   │   ├── Lock.java
    │   │           │   │   ├── MoreThan.java
    │   │           │   │   ├── ScriptValid.java
    │   │           │   │   └── VerifyObject.java
    │   │           │   ├── common
    │   │           │   │   ├── Builder.java
    │   │           │   │   ├── Cacheable.java
    │   │           │   │   └── Lockable.java
    │   │           │   ├── configuration
    │   │           │   │   ├── BeanConfiguration.java
    │   │           │   │   ├── InitCommandLineRunner.java
    │   │           │   │   ├── MQConfiguration.java
    │   │           │   │   ├── RedisConfiguration.java
    │   │           │   │   ├── SwaggerConfiguration.java
    │   │           │   │   ├── ThreadPoolClient.java
    │   │           │   │   ├── WxMaConfiguration.java
    │   │           │   │   └── XxlJobConfig.java
    │   │           │   ├── constant
    │   │           │   │   ├── CCBConstant.java
    │   │           │   │   ├── CommonConstant.java
    │   │           │   │   ├── ErrorMsgConstant.java
    │   │           │   │   └── UserConstant.java
    │   │           │   ├── enums
    │   │           │   │   ├── BillFlowPayTypeEnum.java
    │   │           │   │   ├── BillFlowTradingChannelEnum.java
    │   │           │   │   ├── BillSourceEnum.java
    │   │           │   │   ├── BillStatusEnum.java
    │   │           │   │   ├── BillTypeDictEnum.java
    │   │           │   │   ├── BusinessCodeEnum.java
    │   │           │   │   ├── CCBAreaEnum.java
    │   │           │   │   ├── CCBBillPayStatusEnum.java
    │   │           │   │   ├── CCBContractSigningStatusEnum.java
    │   │           │   │   ├── CCBCredentialsTypeEnum.java
    │   │           │   │   ├── CCBFocusRoomRentStatusEnum.java
    │   │           │   │   ├── CCBProjectTypeEnum.java
    │   │           │   │   ├── CCBSyncStatusEnum.java
    │   │           │   │   ├── CCBTransCodeEnum.java
    │   │           │   │   ├── CalculationFieldEnum.java
    │   │           │   │   ├── CalculationModeEnum.java
    │   │           │   │   ├── CashierTypeEnum.java
    │   │           │   │   ├── DeleteStatusEnum.java
    │   │           │   │   ├── EnumInterface.java
    │   │           │   │   ├── ErrorCodeEnum.java
    │   │           │   │   ├── HzfPayTypeEnum.java
    │   │           │   │   ├── MeterReadingSourceEnum.java
    │   │           │   │   ├── OperateLogTypeEnum.java
    │   │           │   │   ├── PicTypeEnum.java
    │   │           │   │   ├── PlatformEnum.java
    │   │           │   │   ├── PriceConfigEnum.java
    │   │           │   │   ├── PriceDictConfigEnum.java
    │   │           │   │   ├── RentContractConfirmStatusEnum.java
    │   │           │   │   ├── RentContractSigningStatusEnum.java
    │   │           │   │   ├── RentContractSigningTypeEnum.java
    │   │           │   │   ├── RentStatusEnum.java
    │   │           │   │   └── UserRoleEnum.java
    │   │           │   ├── exception
    │   │           │   │   ├── BaseException.java
    │   │           │   │   ├── JwtAuthenticationException.java
    │   │           │   │   ├── LackParameterException.java
    │   │           │   │   └── LockException.java
    │   │           │   ├── mybatis
    │   │           │   │   ├── MyMetaObjectHandler.java
    │   │           │   │   └── MybatisPlusConfig.java
    │   │           │   ├── properties
    │   │           │   │   ├── AliyunOSSProperties.java
    │   │           │   │   ├── JXSaasProperties.java
    │   │           │   │   ├── JwtTokenProperties.java
    │   │           │   │   ├── SmsProperties.java
    │   │           │   │   ├── ThreadPoolProperties.java
    │   │           │   │   ├── WxMaProperties.java
    │   │           │   │   └── WxMpProperties.java
    │   │           │   └── security
    │   │           │       ├── AuthenticationContextHolder.java
    │   │           │       ├── Authorityable.java
    │   │           │       ├── JwtAuthenticationFilter.java
    │   │           │       ├── MySimpleGrantedAuthority.java
    │   │           │       ├── UserAuthenticationFailureHandler.java
    │   │           │       ├── UserTokenAuthenticationProvider.java
    │   │           │       ├── UserTokenAuthenticationToken.java
    │   │           │       └── WebSecurityConfig.java
    │   │           ├── controller
    │   │           │   └── UserController.java
    │   │           ├── dto
    │   │           │   ├── ManagerAccountDTO.java
    │   │           │   ├── UserInfoDTO.java
    │   │           │   ├── request
    │   │           │   │   ├── BaseListRequestDTO.java
    │   │           │   │   ├── BaseRequestDTO.java
    │   │           │   │   └── user
    │   │           │   │       ├── ManagerAccountRequestDTO.java
    │   │           │   │       └── UserLoginRequestDTO.java
    │   │           │   └── response
    │   │           │       ├── ResponseDTO.java
    │   │           │       ├── ResponseStatus.java
    │   │           │       └── user
    │   │           │           ├── LandlordInfoResponseDTO.java
    │   │           │           ├── ManagerAccountResponseDTO.java
    │   │           │           └── UserResponseDTO.java
    │   │           ├── entity
    │   │           │   ├── BaseEntity.java
    │   │           │   ├── OperateLog.java
    │   │           │   └── User.java
    │   │           ├── handler
    │   │           │   └── demo
    │   │           │       ├── CommandJobHandler.java
    │   │           │       ├── DemoJobHandler.java
    │   │           │       ├── HttpJobHandler.java
    │   │           │       └── ShardingJobHandler.java
    │   │           ├── manager
    │   │           │   ├── AuthenticationManager.java
    │   │           │   ├── BusinessCodeManager.java
    │   │           │   ├── CacheManager.java
    │   │           │   ├── CdocManager.java
    │   │           │   ├── LockManager.java
    │   │           │   ├── OperateLogManager.java
    │   │           │   ├── OssManager.java
    │   │           │   ├── QiWechatManager.java
    │   │           │   ├── SmsManager.java
    │   │           │   ├── SpringContrextUtils.java
    │   │           │   ├── WxMaManager.java
    │   │           │   └── mq
    │   │           │       ├── consumer
    │   │           │       │   ├── MQConsumer.java
    │   │           │       │   └── RedisMQConsumer.java
    │   │           │       └── producer
    │   │           │           ├── MQProducer.java
    │   │           │           └── RedisMQProducer.java
    │   │           ├── mapper
    │   │           │   └── user
    │   │           │       ├── OperateLogMapper.java
    │   │           │       └── UserMapper.java
    │   │           ├── plugin
    │   │           │   └── CodeGenerator.java
    │   │           ├── receiver
    │   │           │   ├── AbstractReceiver.java
    │   │           │   └── log
    │   │           │       └── OperateLogReceiver.java
    │   │           ├── service
    │   │           │   ├── UserService.java
    │   │           │   └── impl
    │   │           │       └── UserServiceImpl.java
    │   │           ├── utils
    │   │           │   ├── AnalyzeUtil.java
    │   │           │   ├── Assert.java
    │   │           │   ├── BeanCopyUtils.java
    │   │           │   ├── BeanMapUtils.java
    │   │           │   ├── BeanUtil.java
    │   │           │   ├── DateUtil.java
    │   │           │   ├── EnumUtil.java
    │   │           │   ├── FreemarkerUtil.java
    │   │           │   ├── HttpUtil.java
    │   │           │   ├── JAXBTool.java
    │   │           │   ├── JpushUtil.java
    │   │           │   ├── JwtTokenUtil.java
    │   │           │   ├── LogUtil.java
    │   │           │   ├── MoneyUtil.java
    │   │           │   ├── NetTool.java
    │   │           │   ├── NetUtil.java
    │   │           │   ├── NumberToCN.java
    │   │           │   ├── RegexUtil.java
    │   │           │   ├── RequestUtil.java
    │   │           │   ├── ResponseUtil.java
    │   │           │   ├── StringUtil.java
    │   │           │   ├── XMathUtil.java
    │   │           │   └── json
    │   │           │       ├── GsonTypeAdapter.java
    │   │           │       └── GsonUtil.java
    │   │           └── vo
    │   │               ├── payment
    │   │               │   ├── CashierParamVO.java
    │   │               │   ├── PayNotifyVO.java
    │   │               │   └── SyncUserInfoParamVo.java
    │   │               ├── user
    │   │               │   ├── OperateLogVO.java
    │   │               │   └── WxUserInfoVO.java
    │   │               └── wechat
    │   │                   └── CollectionMsgVO.java
    │   └── resources
    │       ├── application-local.yml
    │       ├── application.yml
    │       ├── keystore
    │       │   ├── jwt-dev.keystore
    │       │   ├── jwt-pre.keystore
    │       │   ├── jwt-prod.keystore
    │       │   └── jwt-staging2.keystore
    │       ├── logback.xml
    │       ├── mapper
    │       │   ├── OperateLogMapper.xml
    │       │   └── UserMapper.xml
    │       ├── static
    │       │   ├── pay-result.html
    │       │   ├── pay-service.html
    │       │   ├── privacy-policy.html
    │       │   └── user-protocol.html
    │       └── templates
    │           └── rent-contract.ftl
    └── test
        ├── java
        │   └── com
        │       ├── huizhaofang
        │       └── jeffrey
        │           ├── ZhifangguanjiaApplicationTests.java
        │           ├── manager
        │           │   ├── SmsManagerTest.java
        │           │   └── WxMaManagerTest.java
        │           ├── service
        │           │   └── impl
        │           └── utils
        │               └── FreemarkerUtilTest.java
        └── resources
            └── logback-test.xml

119 directories, 366 files

```

### 注意事项
* 启动前记得修改配置文件
