package top.hting.htingstock.model.enums;

import lombok.Getter;

@Getter
public enum JuheApiEnum {


    /**
     * 深市股票
     */
    SZALL("szall", "深市股票"),

    /**
     * 沪市股票
     */
    SHALL("shall", "沪市股票");

    private String key;
    private String name;

    private JuheApiEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }



}
