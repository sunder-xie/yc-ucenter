package com.ai.yc.ucenter.constants.eunm;

/**
 * Created by astraea on 2015/7/27.
 */
public final class Constants {

    private Constants() {
        // non
    }

    /**
     * 注册方式
     * to  UcMembersServiceAtomImpl.getUsername
     * @author Administrator
     *
     */
    public final static class LoginWayConstant {
        private LoginWayConstant() {
        }

        //邮箱密码
        public final static String EMAIL_PASS = "1";
        //手机密码
        public final static String MOBILE_PASS = "2";     
        //手机动态密码 
        public final static String MOBILE_DYNA = "3";     
        //用户名密码
        public final static String USER_PASS = "4";     
        
        
    }

    /**
     * 操作码生成 移动电话/邮件
     * to  UcMembersBusinessService.insertMember
     * @author Administrator
     *
     */
    public final static class UserInfoConstant {
        private UserInfoConstant() {
        }

        //邮箱
        public final static String EMAIL_INFO= "1";
        //手机
        public final static String MOBILE_INFO = "2";     

    }
    
    /**
     * 获取用户信息返回结果
     * to  UcMembersBusinessService.insertMember
     * @author Administrator
     *
     */
    public final static class GetUcMembersResultConstants {
        private GetUcMembersResultConstants() {
        }

        //成功
        public final static int SUCCESS_CODE= 1;
        //失败
        public final static int FAIL_CODE = -1;     
        //未激活
        public final static int NO_ACTIV = -12;     
        //输入信息不能为空
        public final static int NOT_EMPTY= -16;  
    }
    
    /**
     * 码类型
     * to  UcMembersBusinessService.insertMember
     * @author Administrator
     *
     */
    public  final static class OperationtypeConstants {

    	private OperationtypeConstants(){}
    	
    	 /**
         * 手机激活码
         */
    	public static final String MOBILE_ACTIV= "1";
    	 /**
         * 手机验证码
         */
    	public static final String MOBILE_VALI= "2";
    	 /**
         * 手机动态密码
         */
    	public static final String DYN_PASS= "3";
    	 /**
         * 邮箱激活码
         */
    	public static final String EMAIL_ACTIV= "4";
    	 /**
         * 邮箱验证码
         */
    	public static final String EMAIL_VALI= "5";
    	 /**
         * 密码操作验证码
         */
    	public static final String PASS_VALI= "6";
    	
    	
    }
}
