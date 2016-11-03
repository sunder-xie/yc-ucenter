package com.ai.yc.ucenter.api.members.param.checke;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersCheckeMobileRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键 
     */
    private Integer uid;
    
    /**
     * 移动电话
     */
    private String mobilephone;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
    
   


    
    
}
