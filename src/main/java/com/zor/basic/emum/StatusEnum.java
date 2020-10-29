package com.zor.basic.emum;

import lombok.Getter;

/**
 * @author zqq
 * @date 2020/5/15
 */
@Getter
public enum StatusEnum {
    YES("1"),
    NO("0");

    private String code;

    StatusEnum(String code) {
        this.code = code;
    }

    public static StatusEnum getEnum(String code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(StatusEnum.getEnum("1"));
    }
}
