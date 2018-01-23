package com.lock.util.redis;

/**
 * @ClassName: JedisKey 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author CongZN
 * @date 2016-10-10 下午05:01:01 
 * @version V1.0 
 *
 * <p> 修改历史</p>
 * <p> 序号 日期 修改人 修改原因</p>
 */
public class JedisKey {
	
	public static final String TEMP_USER_UNIOND = "temp_user_unionid_";
	
	public static final String TEMP_USER_OPENID = "temp_user_openid_";
	
	public static final String USER_OPENID = "user_openid_";
	public static final String USER_UNIONID = "user_unionid_";
	public static final String USER_MOBILE = "user_mobile_";
	
	//key_渠道_至
	public static final String USER_MAPPING_MOBILE = "user_mapping_mobile_";
	public static final String USER_MAPPING_OPENID = "user_mapping_openid_";
	
	public static final String CANNEL_CODE = "channel_code_";
	
	public static final Integer TEMP_USER_EXPIRE_TIME = 3600*24;
	public static final Integer USER_OPENID_EXPIRE_TIME = 3600*24;
	public static final Integer USER_MOBILE_EXPIRE_TIME = 3600*24;
	
}
