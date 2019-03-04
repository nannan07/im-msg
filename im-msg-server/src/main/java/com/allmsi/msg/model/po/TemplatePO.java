package com.allmsi.msg.model.po;

import java.util.Arrays;
import java.util.Date;


public class TemplatePO {
    private String id;

    private String code;

    private Date cTime;

    private Date uTime;

    private Integer del;

    private byte[] text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

	@Override
	public String toString() {
		return "TemplatePO [id=" + id + ", code=" + code + ", cTime=" + cTime + ", uTime=" + uTime + ", del=" + del
				+ ", text=" + Arrays.toString(text) + "]";
	}
    
}