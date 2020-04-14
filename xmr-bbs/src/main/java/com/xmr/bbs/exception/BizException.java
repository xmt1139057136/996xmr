package com.xmr.bbs.exception;

import com.xmr.bbs.myenums.CustomizeErrorCode;

public class BizException extends  RuntimeException{
    @Override
    public String getMessage() {
        return super.getMessage();
    }
    public BizException(CustomizeErrorCode customizeErrorCode) {
        super(customizeErrorCode.getMessage());
    }
}
