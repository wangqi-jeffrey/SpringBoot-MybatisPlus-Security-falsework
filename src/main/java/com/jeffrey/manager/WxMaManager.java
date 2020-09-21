package com.jeffrey.manager;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import com.jeffrey.context.common.Builder;
import com.jeffrey.context.configuration.WxMaConfiguration;
import com.jeffrey.context.properties.WxMaProperties;
import com.jeffrey.context.properties.WxMpProperties;
import com.jeffrey.dto.response.user.UserResponseDTO;
import com.jeffrey.service.UserService;
import com.jeffrey.utils.DateUtil;
import com.jeffrey.utils.XMathUtil;
import com.jeffrey.vo.wechat.CollectionMsgVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 微信小程序相关api
 *
 * @author WQ
 * @date 2020/09/11 10:56 AM
 */
@Slf4j
@Component
public class WxMaManager {

    @Autowired
    private UserService userService;

    @Autowired
    private WxMpProperties wxMpProperties;

    @Autowired
    private WxMaProperties wxMaProperties;

    public void sendCollectionMsg(CollectionMsgVO collectionMsgVO) throws WxErrorException {
        UserResponseDTO userDto = userService.getUserDtoByPhone(collectionMsgVO.getPhone());

        final WxMaService wxService = WxMaConfiguration.getMaService(userDto.getAppid());
        WxMaUniformMessage uniformMessage = new WxMaUniformMessage();
        uniformMessage.setMpTemplateMsg(true);
        uniformMessage.setToUser(userDto.getOpenId());
        uniformMessage.setAppid(wxMpProperties.getAppId());
        uniformMessage.setTemplateId(wxMpProperties.getCollectionMsgTplId());

        WxMaUniformMessage.MiniProgram miniProgram = new WxMaUniformMessage.MiniProgram();
        miniProgram.setAppid(wxMaProperties.getConfigs().get(0).getAppid());
        miniProgram.setPagePath(wxMaProperties.getBillPage());
        uniformMessage.setMiniProgram(miniProgram);
        uniformMessage.addData(Builder.of(WxMaTemplateData::new)
                .with(WxMaTemplateData::setName, "first")
                .with(WxMaTemplateData::setValue, "尊敬的客户您好，您有待缴费账单未处理。")
                .build());
        uniformMessage.addData(Builder.of(WxMaTemplateData::new)
                .with(WxMaTemplateData::setName, "keyword1")
                .with(WxMaTemplateData::setValue, String.format("应还款%s元", XMathUtil.fen2yuanStr(collectionMsgVO.getAmount())))
                .build());
        uniformMessage.addData(Builder.of(WxMaTemplateData::new)
                .with(WxMaTemplateData::setName, "keyword2")
                .with(WxMaTemplateData::setValue, DateUtil.formatChineseDate(collectionMsgVO.getCollectionDate()))
                .build());
        uniformMessage.addData(Builder.of(WxMaTemplateData::new)
                .with(WxMaTemplateData::setName, "remark")
                .with(WxMaTemplateData::setValue, "请尽快处理以免产生滞纳金")
                .build());

        wxService.getMsgService().sendUniformMsg(uniformMessage);
    }
}