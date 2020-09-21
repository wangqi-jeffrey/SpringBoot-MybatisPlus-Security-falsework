package com.jeffrey.context.enums;

/**
 *  * Description: 建融城市编码
 *  * @author 金泽强
 *  * @date 2020/08/25 6:00 下午
 *  
 */
public enum CCBAreaEnum {

    LUO_HU("440303", "罗湖区"),
    FU_TIAN("440304", "福田区"),
    NAN_SHAN("440305", "南山区"),
    BAO_AN("440306", "宝安区"),
    LONG_GANG("440307", "龙岗区"),
    YAN_TIAN("440308", "盐田区"),
    GUANG_MING_XIN("440309", "光明新区"),
    GUANG_MING("440309", "光明区"),
    PING_SHAN_XIN("440310", "坪山新区"),
    PING_SHAN("440310", "坪山区"),
    DA_PENG_XIN("440311", "大鹏新区"),
    LONG_HUA_XIN("440312", "龙华新区"),
    LONG_HUA("440312", "龙华区"),
    ;

    private String districtCode;
    private String district;

    private CCBAreaEnum(String districtCode, String district) {
        this.districtCode = districtCode;
        this.district = district;
    }

    public static CCBAreaEnum getCCBAreaEnumByDistrict(String district) {
        for (CCBAreaEnum ccbAreaEnum : CCBAreaEnum.values()) {
            if (ccbAreaEnum.getDistrict().equals(district)) {
                return ccbAreaEnum;
            }
        }

        return null;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getDistrict() {
        return district;
    }

}
