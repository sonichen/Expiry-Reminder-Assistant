package com.cyj.pojo;

import java.util.Map;

public class WxSubscribeMsg {

    // 接收人id
    private String touser;
    // 模板id
    private String template_id;
    // 跳转小程序
    private String page;
    // 模板数据
    private Map<String, WxTemplateValue> data;
    // 跳转小程序类型 默认正式版
    private String miniprogram_state;
    // 语言类型 默认中文
    private String lang = "zh_CN";

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, WxTemplateValue> getData() {
        return data;
    }

    public void setData(Map<String, WxTemplateValue> data) {
        this.data = data;
    }

    public String getMiniprogram_state() {
        return miniprogram_state;
    }

    public void setMiniprogram_state(String miniprogram_state) {
        this.miniprogram_state = miniprogram_state;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
