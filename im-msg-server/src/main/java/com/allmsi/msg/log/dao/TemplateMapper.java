package com.allmsi.msg.log.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.allmsi.msg.model.po.TemplatePO;

@Mapper
public interface TemplateMapper {

	TemplatePO selectByCode(String code);

	TemplatePO selectByConfig(@Param("sysId") String sysId, @Param("templateType") String templateType,
			@Param("msgType") String msgType);

	TemplatePO getMsgUrlTemplate(@Param("sysFlag") String sysFlag,@Param("msgType") String msgType);
}