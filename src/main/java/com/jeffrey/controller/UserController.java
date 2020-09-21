package com.jeffrey.controller;

import com.jeffrey.dto.request.user.UserLoginRequestDTO;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.dto.response.user.UserResponseDTO;
import com.jeffrey.manager.AuthenticationManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: C端租客控制器
 *
 * @author WQ
 * @date 2020/8/28 3:15 PM
 */
@Api(description = "C端租客相关接口")
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "获取登录凭证")
    @ApiResponses(value = {
            @ApiResponse(code = 300, message = "操作失败"),
            @ApiResponse(code = 200, message = "操作成功")
    })
    @PostMapping("/oauth/ticket")
    public ResponseDTO<UserResponseDTO> getUserAccessToken(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseDTO.success(authenticationManager.getUserAccessToken(userLoginRequestDTO));
    }

    @ApiOperation(value = "删除会话")
    @ApiResponses(value = {
            @ApiResponse(code = 300, message = "操作失败"),
            @ApiResponse(code = 200, message = "操作成功")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "token", value = "用户标识", paramType = "header", required = true),
    })
    @PreAuthorize("hasRole('TENANT')")
    @DeleteMapping("/session")
    public ResponseDTO<?> deleteUserAccessToken() {
        authenticationManager.deleteUserAccessToken();
        return ResponseDTO.success();
    }

}