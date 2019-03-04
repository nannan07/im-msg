package com.allmsi.msg.log.dao;

import com.allmsi.msg.model.po.HttpLogPO;

public interface HttpLogMapper {

    int insertSelective(HttpLogPO record);

}