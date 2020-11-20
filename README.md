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
├── Dockerfile
├── README.md
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

59 directories, 181 files

```

### 注意事项
* 启动前记得修改配置文件
