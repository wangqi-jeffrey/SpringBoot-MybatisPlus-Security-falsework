create table if not exists t_user
(
	f_id int unsigned auto_increment
		primary key,
	f_user_code varchar(50) not null comment '用户编号',
	f_phone varchar(20) null comment '手机号',
	f_appid varchar(128) null comment '小程序appid',
	f_open_id varchar(64) null comment '小程序的openid',
	f_union_id varchar(64) null comment 'unionid',
	f_nick_name varchar(128) null comment '用户昵称',
	f_avatar_url varchar(256) null comment '用户头像图片的 URL',
	f_gender tinyint(1) default 0 not null comment '用户性别',
	f_country varchar(64) null comment '用户所在国家',
	f_province varchar(64) null comment '用户所在省份',
	f_city varchar(64) null comment '用户所在城市',
	f_language varchar(64) null comment '语言',
	f_last_login_time datetime null comment '最后一次登录时间',
	f_create_time datetime not null comment '创建时间',
	f_update_time datetime not null comment '修改时间',
	f_is_delete int default 0 null comment '是否删除：0-否 1-是'
)
comment 'C端用户信息' charset=utf8mb4;

create index idx_f_phone
	on t_user (f_phone);

create table if not exists t_operate_log
(
	f_id int unsigned auto_increment
		primary key,
	f_platform varchar(20) null comment '客户端类型',
	f_version int null comment '客户端版本号',
	f_client_ip varchar(32) null comment '请求客户端IP',
	f_module_code varchar(50) not null comment '所属模块编号',
	f_operate_type_code varchar(50) not null comment '操作类型编号',
	f_operator_code varchar(50) not null comment '操作人编号',
	f_business_key varchar(50) null comment '操作业务主键',
	f_house_code varchar(50) null comment '操作房源编号',
	f_room_code varchar(50) null comment '操作房间编号',
	f_content varchar(256) null comment '操作内容',
	f_create_time datetime not null comment '创建时间',
	f_update_time datetime not null comment '修改时间',
	f_is_delete int default 0 null comment '是否删除：0-否 1-是'
)
comment '用户操作日志表' charset=utf8mb4;



