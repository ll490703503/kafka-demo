package com.melon.kafkademo.common;

import com.melon.kafkademo.common.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResponse {

    /**
     * 响应代码
     */
    private Integer errorCode;

    /**
     * 响应消息
     */
    private String errorMessage;

    /**
     * 响应结果
     */
    private Object response;

    public RestResponse() {
    }


    /**
     * 成功
     *
     * @return
     */
    public static RestResponse success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static RestResponse success(Object data) {
        RestResponse response = new RestResponse();
        response.setErrorCode(RestEnum.SUCCESS.getErrorCode());
        response.setErrorMessage(RestEnum.SUCCESS.getErrorMessage());
        response.setResponse(data);
        return response;
    }

    /**
     * 失败
     */


    /**
     * 失败
     */
    public static RestResponse error(Integer code, String message) {
        RestResponse response = new RestResponse();
        response.setErrorCode(code);
        response.setErrorMessage(message);
        response.setResponse(null);
        return response;
    }

    /**
     * 失败
     */
    public static RestResponse error(String message) {
        RestResponse response = new RestResponse();
        response.setErrorCode(RestEnum.INTERNAL_SERVER_ERROR.getErrorCode());
        response.setErrorMessage(message);
        response.setResponse(null);
        return response;
    }

    public static RestResponse error(RestEnum restEnum) {
        RestResponse response = new RestResponse();
        response.setErrorCode(restEnum.getErrorCode());
        response.setErrorMessage(restEnum.getErrorMessage());
        response.setResponse(null);
        return response;
    }

    @Override
    public String toString() {
        return JsonHelper.obj2String(this);
    }

}


