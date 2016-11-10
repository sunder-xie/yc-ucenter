package com.ai.yc.ucenter.service.business.members.impl;




import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.base.ResponseCode;
import com.ai.yc.ucenter.api.members.param.base.ResponseMessage;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;

import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;

import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;
import com.ai.yc.ucenter.util.OperationValidateUtils;
import com.ai.yc.ucenter.util.UcmembersValidators;



@Component
@Transactional
public class UcMembersOperationBusinessService implements IUcMembersOperationBusinessService {
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	@Override
	public UcMembersGetOperationcodeResponse saveOperationcode(UcMembersGetOperationcodeRequest request) {
		UcMembersGetOperationcodeResponse response = new UcMembersGetOperationcodeResponse();
		String operationtype = request.getOperationtype();
		//手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取
		
		
		//校验：Uid只有手机/邮箱验证码和邮箱激活码用到，有值。
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype) || OperationtypeConstants.MOBILE_VALI.equals(operationtype)
				|| OperationtypeConstants.EMAIL_VALI.equals(operationtype)){
			if(!StringUtils.isNotBlank(request.getUid()+"")){
				ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.EXIST_ERROR, "Uid不能为空");	
			
				response.setCode(responseCode);
				response.setMessage(responseMessage);
				
				return response;
			}
		}
		
		//1、生成手机激活码操作
		if(OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号已被注册");	
			
				response.setCode(responseCode);
				response.setMessage(responseMessage);
				
				return response;
			}
			
			//如果没有注册过，需根据手机号注册账号，账号状态为未激活，生成并返回手机激活码		
		
		}//2、手机动态密码
		else if(OperationtypeConstants.DYN_PASS.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号已被注册");	
				response.setCode(responseCode);
				response.setMessage(responseMessage);
				return response;
			}
		}

		String operationcode =  iUcMembersOperationAtomService.saveOperationcode(request);
		if(StringUtils.isNotBlank(operationcode)){
			ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.SUCCESS_CODE, "成功");
			ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.SUCCESS_CODE, "成功");	
			response.setOperationcode(operationcode);
			response.setCode(responseCode);
			response.setMessage(responseMessage);
		}
		return response;
	}

	@Override
	@Transactional
	public UcMembersResponse checkActiveMembe(UcMembersActiveRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		String operationtype = request.getOperationtype();
		String operationcode = request.getOperationcode();
		//判断激活码
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype)
				||OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			if(!OperationValidateUtils.mobileActivAndDyan(request.getUid(), operationcode)){
				ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期");	
				response.setCode(responseCode);
				response.setMessage(responseMessage);
			}
			
			int resultActive =   iUcMembersOperationAtomService.updateActiveMember(request);
			if(resultActive>0){
				ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.SUCCESS_CODE, "成功");
				ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.SUCCESS_CODE, "激活成功");	
				response.setCode(responseCode);
				response.setMessage(responseMessage);
			}
			
		}//判断验证码
		else{
			if(!OperationValidateUtils.emailVali(request.getUid(), operationcode)){
				ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期");	
				response.setCode(responseCode);
				response.setMessage(responseMessage);
			}
		}
		return response;
	}

}
