package com.jeffrey.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeffrey.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * C端用户信息 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2020-09-02
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
