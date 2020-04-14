package com.xmr.bbs.test;

import com.xmr.bbs.dto.QQopenIdObj;
import org.junit.Test;

public class TestQQOpenId {
    private String callbackStr="callback( {\"client_id\":\"101794828\",\"openid\":\"97FC7EEE748D5E2EADD1F78931FFCF2D\"} );";

    @Test
    public void testgetopenid(){
        QQopenIdObj qQopenIdObj = new QQopenIdObj(callbackStr);
        System.out.println(qQopenIdObj.getOpenid());
    }

}
