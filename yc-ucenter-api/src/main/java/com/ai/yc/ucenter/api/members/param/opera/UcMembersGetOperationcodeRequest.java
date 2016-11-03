package com.ai.yc.ucenter.api.members.param.opera;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersGetOperationcodeRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 用户id(只有手机/邮箱验证码和邮箱激活码用到，有值。)
     */
    private String uid;

    /**
     * 移动电话/邮件
     */
    private String userinfo;
    
    /**
     * 操作类型
			1：手机激活码
			2：手机验证码
			3：手机动态密码
			4：邮箱激活码
			5：邮箱验证码
			6：密码操作验证码
     */
    private String operationtype;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	public String getOperationtype() {
		return operationtype;
	}

	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}   
    
    
}
