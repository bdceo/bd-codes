package com.bdsoft.bdceo.cas.config;

/**
 * 基于xml配置文件的属性配置策略
 */
public class XmlConfigStrategyImpl extends BaseConfigStrategy {

    @Override
    public void init() {
        // TODO load xml ...
    }

    @Override
    protected String get(ConfigKey key) {
        // TODO read property from xml
        return null;
    }

}
