package com.jeffrey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jeffrey.context.common.Builder;
import com.jeffrey.context.enums.DeleteStatusEnum;
import com.jeffrey.context.enums.UserRoleEnum;
import com.jeffrey.dto.response.user.UserResponseDTO;
import com.jeffrey.entity.User;
import com.jeffrey.mapper.user.UserMapper;
import com.jeffrey.service.UserService;
import com.jeffrey.utils.StringUtil;
import com.jeffrey.vo.user.WxUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: C端用户服务接口实现
 *
 * @author WQ
 * @date 2020/08/28 6:00 PM
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO getUserInfoAngLogin(WxUserInfoVO wxUserInfoVO) {
        // 先从用户表获取该用户是否已注册
        User user = getUserByPhone(wxUserInfoVO.getPhone());
        if (user == null) {
            // if (CollectionUtils.isEmpty(rentContractTenantList)) {
            //     throw new BaseException(ErrorCodeEnum._10028);
            // }
            // 创建用户
            user = new User();
            BeanUtils.copyProperties(wxUserInfoVO, user);
            user.setLastLoginTime(new Date());
            user.setUserCode(StringUtil.generateUserCode(UserRoleEnum.ROLE_TENANT.getPrefix()));
            userMapper.insert(user);
        } else {
            BeanUtils.copyProperties(wxUserInfoVO, user);
            user.setLastLoginTime(new Date());
            userMapper.updateById(user);
        }
        return Builder.of(UserResponseDTO::new)
                .with(UserResponseDTO::setId, user.getId())
                .with(UserResponseDTO::setUserCode, user.getUserCode())
                .with(UserResponseDTO::setPhone, user.getPhone())
                .with(UserResponseDTO::setOpenId, user.getOpenId())
                .with(UserResponseDTO::setNickName, user.getNickName())
                .with(UserResponseDTO::setAvatarUrl, user.getAvatarUrl())
                .with(UserResponseDTO::setLastLoginTime, user.getLastLoginTime())
                .build();
    }

    private User getUserByPhone(String phone) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("f_phone", phone)
                .eq("f_is_delete", DeleteStatusEnum.NO.getStatus()));
    }

    @Override
    public UserResponseDTO getUserDtoByPhone(String phone) {
        User user = getUserByPhone(phone);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO getUserDtoById(Integer id) {
        User user = userMapper.selectById(id);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    /**
     * 通过用户编号获取用户
     *
     * @param userCodes
     * @return
     */
    @Override
    public List<UserResponseDTO> getUserByUserCodes(List<String> userCodes) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("f_user_code", userCodes);
        queryWrapper.eq("f_is_delete", DeleteStatusEnum.NO.getStatus());
        List<User> users = userMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(users)) {
            users.forEach(user -> {
                UserResponseDTO userResponseDTO = new UserResponseDTO();
                BeanUtils.copyProperties(user, userResponseDTO);
                userResponseDTOS.add(userResponseDTO);
            });
        }

        return userResponseDTOS;
    }
}