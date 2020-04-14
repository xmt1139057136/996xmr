package com.xmr.bbs.service;

import com.xmr.bbs.dao.AdMapper;
import com.xmr.bbs.modal.Ad;
import com.xmr.bbs.modal.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdMapper adMapper;

    public List<Ad> listAds(String name){
        AdExample adExample = new AdExample();
        AdExample.Criteria criteria = adExample.createCriteria();
        adExample.setOrderByClause("gmt_create desc");
        criteria.andStatusEqualTo(1);
        criteria.andGmtStartLessThan(System.currentTimeMillis());
        criteria.andGmtEndGreaterThan(System.currentTimeMillis());
        criteria.andPostionEqualTo(name);
        return adMapper.selectByExample(adExample);
    }

}
