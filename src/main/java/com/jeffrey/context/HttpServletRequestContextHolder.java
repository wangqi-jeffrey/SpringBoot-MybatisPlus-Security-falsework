package com.jeffrey.context;

import com.jeffrey.context.constant.CommonConstant;
import com.jeffrey.context.enums.PlatformEnum;
import com.jeffrey.context.enums.UserRoleEnum;
import com.jeffrey.context.security.AuthenticationContextHolder;
import com.jeffrey.context.security.UserTokenAuthenticationToken;
import com.jeffrey.dto.ManagerAccountDTO;
import com.jeffrey.dto.UserInfoDTO;
import com.jeffrey.dto.request.BaseRequestDTO;
import com.jeffrey.utils.NetUtil;
import com.jeffrey.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Description: HttpServletRequest上下文
 *
 * @author WQ
 * @date 2020/08/27 7:55 PM
 */
@Slf4j
public class HttpServletRequestContextHolder {

    public static BaseRequestDTO getBaseRequestAttributes(HttpServletRequest httpServletRequest) {
        BaseRequestDTO baseRequestDTO = new BaseRequestDTO();
        String version = httpServletRequest.getHeader(CommonConstant.HEADER_VERSION);
        String platform = httpServletRequest.getHeader(CommonConstant.HEADER_PLATFORM);
        if (StringUtil.isNotBlank(version)) {
            baseRequestDTO.setVersion(Integer.parseInt(version));
        }
        if (StringUtil.isNotBlank(platform)) {
            baseRequestDTO.setPlatform(PlatformEnum.valueOf(platform));
        }
        String clientIP = NetUtil.getClientIP(httpServletRequest);
        baseRequestDTO.setClientIp(clientIP);

        // 校验用户角色，分别填充
        UserTokenAuthenticationToken userAuthentication = AuthenticationContextHolder.getUserAuthentication();
        if (userAuthentication == null) {
            return baseRequestDTO;
        }

        Collection<GrantedAuthority> authorities = userAuthentication.getAuthorities();
        if (authorities.stream().anyMatch(authority -> UserRoleEnum.ROLE_TENANT.name()
                .equals(authority.getAuthority()))) {
            // C端租客
            UserInfoDTO userInfoDTO = AuthenticationContextHolder.getUserDetails(userAuthentication, UserInfoDTO.class);
            baseRequestDTO.setUserInfoDTO(userInfoDTO);
        } else if (authorities.stream().anyMatch(authority -> (UserRoleEnum.ROLE_LANDLORD.name()
                .equals(authority.getAuthority()) || UserRoleEnum.ROLE_BUTLER.name()
                .equals(authority.getAuthority())))) {
            // B端管理员
            ManagerAccountDTO managerAccountDTO = AuthenticationContextHolder.getUserDetails(userAuthentication,
                    ManagerAccountDTO.class);
            baseRequestDTO.setManagerAccountDTO(managerAccountDTO);
        }

        return baseRequestDTO;
    }

}