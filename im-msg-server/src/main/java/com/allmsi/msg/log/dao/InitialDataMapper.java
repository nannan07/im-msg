package com.allmsi.msg.log.dao;

import com.allmsi.msg.model.po.InitialDataPO;

public interface InitialDataMapper {
    
    int insertSelective(InitialDataPO record);

}