package com.jeffrey.service;

import com.jeffrey.dto.response.user.UserResponseDTO;
import com.jeffrey.vo.user.WxUserInfoVO;

import java.util.List;

/**
 * Description: C端用户服务接口
 *
 * @author WQ
 * @date 2020/08/28 6:00 PM
 */
public interface UserService {

    /**
     * 登录时获取用户信息
     *
     * @param wxUserInfoVO
     * @return
     */
    UserResponseDTO getUserInfoAngLogin(WxUserInfoVO wxUserInfoVO);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    UserResponseDTO getUserDtoByPhone(String phone);

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    UserResponseDTO getUserDtoById(Integer id);

    /**
     * 通过用户编号获取用户
     *
     * @param userCodes
     * @return
     */
    List<UserResponseDTO> getUserByUserCodes(List<String> userCodes);
}