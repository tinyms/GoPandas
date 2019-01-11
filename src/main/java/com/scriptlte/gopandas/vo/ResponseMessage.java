package com.scriptlte.gopandas.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {

    /**
     * 数据获取成功
     */
    public static final String RESPONSE_SUCCESS = "0";
    /**
     * 数据获取失败
     */
    public static final String RESPONSE_FAIL    = "-1";

    /**
     * 数据
     */
    private Object data;

    /**
     *状态信息
     */
    private String status;

    /**
     * 异常数据
     */
    private Object errorinfo;

    public ResponseMessage(String status,Object info) {
        this.status = status;
        if (RESPONSE_SUCCESS.equals(status)){
            this.data = info;
            this.errorinfo = "";
        }
        if (RESPONSE_FAIL.equals(status)){
            this.errorinfo = info.toString();
            this.data = "";
        }
    }

    public static ResponseMessage GetErrorMessage(Object msg){
        return new ResponseMessage(RESPONSE_FAIL, msg);
    }

    public static ResponseMessage GetSuccessMessage(Object msg){
        return new ResponseMessage(RESPONSE_SUCCESS, msg);
    }

}
