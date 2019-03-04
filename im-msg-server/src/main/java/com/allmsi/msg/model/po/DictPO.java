package com.allmsi.msg.model.po;

public class DictPO {
    private String id;

    private String dName;

    private String dCode;

    private String dParent;

    private Integer del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode == null ? null : dCode.trim();
    }

    public String getdParent() {
        return dParent;
    }

    public void setdParent(String dParent) {
        this.dParent = dParent == null ? null : dParent.trim();
    }

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

}