package com.xmr.bbs.exception;

import com.xmr.bbs.myenums.CustomizeErrorCode;

public class CustomizeException extends RuntimeException {

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    public CustomizeException(CustomizeErrorCode customizeErrorCode) {
        super(customizeErrorCode.getMessage());
    }
}
