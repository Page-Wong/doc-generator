package com.bshhr.docgenerator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "doc-generator")
@Data
public class DocGeneratorConfig {
    /** 获取地址开关 */
    private static boolean addressEnabled;

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        DocGeneratorConfig.addressEnabled = addressEnabled;
    }

}
