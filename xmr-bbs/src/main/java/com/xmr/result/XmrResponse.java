package com.xmr.result;

import java.io.Serializable;

/**
 * 请求返回结果
 */
public class XmrResponse implements Serializable {

    private static final long serialVersionUID = 8125672939123850928L;

    private static final int PARAMETER_INVALID_CODE = 588;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int STATUS_CODE_OK = 200;
    private static final int STATUS_CODE_BAD_REQUEST = 400;

    private static final String SUCCESS_MESSAGE = "OK";
    private static final String ERROR_MESSAGE = "Error";

    public static XmrResponse OK() {
        return new XmrResponse(STATUS_CODE_OK, SUCCESS_MESSAGE);
    }

    public static XmrResponse OK(Object data) {
        return new XmrResponse(STATUS_CODE_OK, SUCCESS_MESSAGE, data);
    }

    public static XmrResponse ERROR(int code) {
        return new XmrResponse(code, ERROR_MESSAGE);
    }

    public static XmrResponse ERROR(int code, String message) {
        return new XmrResponse(code, message);
    }

    public static XmrResponse ERROR(String message) {
        return new XmrResponse(STATUS_CODE_BAD_REQUEST, message);
    }

    public static XmrResponse ERROR() {
        return new XmrResponse(STATUS_CODE_BAD_REQUEST, ERROR_MESSAGE);
    }

    public static XmrResponse ERROR(Object data) {
        return new XmrResponse(INTERNAL_SERVER_ERROR, ERROR_MESSAGE, data);
    }

    public static XmrResponse ERROR(Object data,int code) {
        return new XmrResponse(code, ERROR_MESSAGE, data);
    }

    public static XmrResponse ServerError() {
        return XmrResponse.ERROR(INTERNAL_SERVER_ERROR, "服务器内部错误");
    }

    public static XmrResponse ParameterError() {
        return XmrResponse.ERROR(PARAMETER_INVALID_CODE, "参数错误");
    }

    private int code;
    private String msg;
    private Object data;

    public XmrResponse() {
        code = STATUS_CODE_OK;
        msg = SUCCESS_MESSAGE;
    }

    public XmrResponse(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public XmrResponse(int code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public XmrResponse(Object data) {
        this();
        this.data = data;
    }

    public boolean isOk() {
        return this.code == 0;
    }

    public int getCode() {
        return code;
    }

    public XmrResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public XmrResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public XmrResponse setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}