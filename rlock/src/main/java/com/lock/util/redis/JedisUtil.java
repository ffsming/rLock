package com.lock.util.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JedisUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author CongZN
 * @date 2016-10-10 下午05:01:12 
 * @version V1.0 
 *
 * <p> 修改历史</p>
 * <p> 序号 日期 修改人 修改原因</p>
 */
public class JedisUtil {

	/**
	 * 日志类.
	 */
	private static Logger logger = Logger.getLogger(JedisUtil.class);

	private JedisClient jedisClient;

	/**
	 * 存入
	 * 
	 * @param key
	 * @param
	 * @param
	 *            过期时间
	 */
	public <T> void jedisSet(String key, T o, int time) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			jedis.setex(JedisClient.serialize(key), time, JedisClient.serialize(o));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSet-过期时间:redis数据操作工具类-存入异常:参数无效!key：" + key);
			} else {
				e.printStackTrace();
				logger.error("jedisSet-过期时间:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
	}

	/**
	 * 存入
	 * 
	 * @param <T>
	 * @param key
	 * @param 
	 */
	public <T> void jedisSet(String key, T o) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			jedis.set(JedisClient.serialize(key), JedisClient.serialize(o));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!key:" + key);
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
	}

	/**
	 * 存入Map中的所有
	 * 
	 * @param <T>
	 * @param key
	 * @param obj
	 */
	public void jedisSetMapAll(String key, Map<byte[], byte[]> map) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			jedis.hmset(JedisClient.serialize(key), map);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage() == null) {
				logger.error("jedisSetMapAll:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapAll:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
	}

	/**
	 * 存入Map中的一个
	 * 
	 * @param <T>
	 * @param key
	 * @param obj
	 */
	public void jedisSetMapOne(String key, Object field, Object value) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			jedis.hset(JedisClient.serialize(key), JedisClient.serialize(field), JedisClient.serialize(value));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
	}

	/**
	 * 存入list
	 * @param key
	 * @param value
	 */
	public void jedisSetListOne(String key, Object value){
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			jedis.rpush(JedisClient.serialize(key),JedisClient.serialize(value));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
	}
	/**
	 * 取出list
	 * @param key
	 * @param
	 */
	public List<Object> jedisGetList(String key){
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		List<byte[]> list = new ArrayList<byte[]>();
		List<Object> returnList = new ArrayList<Object>();
		try {
			// 取出(key, 0 , -1)
			list = jedis.lrange(JedisClient.serialize(key) , 0 , -1);
			if(list != null){
				for(byte[] bytes : list){
					returnList.add(JedisClient.unserialize(bytes));
				}
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return returnList;
	}

	/**
	 * 取出list中最后存入的object
	 * @param key
	 * @return
	 */
	public Object jedisGetListOne(String key){
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		Object object = null;
		List<byte[]> list = new ArrayList<byte[]>();
		try {
			// 取出(key, 0 , -1)
			
			list = jedis.lrange(JedisClient.serialize(key) , -1 ,-1);
			if(list != null){
				for(byte[] bytes : list){
					object = JedisClient.unserialize(bytes);
				}
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return object;
	}

	/**
	 * 删除list中最后存入的object
	 * @param key
	 * @return
	 */
	public String jedisDelListOne(String key){
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		String str = null;
		try {
			str = jedis.rpop(key);
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisSetMapOne:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return str;
	}
	/**
	 * 取出Map中的所有
	 * 
	 * @param <T>
	 * @param key
	 * @param obj
	 */
	public Map<Object, Object> jedisGetMapAll(String key) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		Map<byte[], byte[]> jedisMap = null;
		try {
			// 取出
			jedisMap = jedis.hgetAll(JedisClient.serialize(key));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("jedisGetMapAll:redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("jedisGetMapAll:redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}

		Map<Object, Object> map = new HashMap<Object, Object>();
		if (jedisMap != null) {
			for (Map.Entry<byte[], byte[]> entry : jedisMap.entrySet()) {
				map.put(JedisClient.unserialize(entry.getKey()), JedisClient.unserialize(entry.getValue()));
			}
		}

		return map;

	}

	/**
	 * 取出Map中的一个
	 * 
	 * @param <T>
	 * @param key
	 * @param obj
	 */
	public Object jedisGetMapOne(String key, Object field) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		Object obj = null;
		byte[] by = null;
		try {
			// 取出
			by = jedis.hget(JedisClient.serialize(key), JedisClient.serialize(field));

		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		if (by != null) {
			obj = JedisClient.unserialize(by);
		}

		return obj;

	}

	/**
	 * 读取
	 * 
	 * @param <T>
	 * @param key
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public <T> T jedisGet(String key) {
		Object obj = null;
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 取出
			System.out.println("取出 : "+jedis.get(JedisClient.serialize(key)));
			obj = JedisClient.unserialize(jedis.get(JedisClient.serialize(key)));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				 logger.error("redis数据操作工具类-读取异常:参数无效! key:"+key);
			} else {
				logger.error("redis数据操作工具类-读取异常:" + e.getMessage());
			}

		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return (T) obj;
	}
	@SuppressWarnings("unchecked")
	public <T> T jedisGet2(String key) {
		Object obj = null;
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 取出
			System.out.println("取出 : "+jedis.get(JedisClient.serialize(key)));
			obj = jedis.get(key);
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-读取异常:参数无效! key:"+key);
			} else {
				logger.error("redis数据操作工具类-读取异常:" + e.getMessage());
			}
			
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return (T) obj;
	}

	public JedisClient getJedisClient() {
		return jedisClient;
	}

	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * 
	 */
	public <T> void jedisDel(String key) {
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();

		try {
			jedis.del(JedisClient.serialize(key));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!");
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}

	}

	/**
	 * 如果不存在，则存入
	 * @param key
	 * @param o
	 * @return
	 */
	public Integer jedisSetNX(String key, String o) {
		Integer count = 0;
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			count = jedis.setnx(JedisClient.serialize(key), JedisClient.serialize(o)).intValue();
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!key:" + key);
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return count;
	}
	public String jedisSet(String key, String value, String nxxx, String expx , long expireTime) {
		String flag = "";
		// 标准部分开始
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			flag = jedis.set(key, value,nxxx,expx,expireTime);
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!key:" + key);
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return flag;
	}
	/**
	 * 查询是否存在
	 * @param key
	 * @param o
	 * @return
	 */
	public Boolean jedisExist(String key) {
		// 标准部分开始
		boolean flag = false;
		Jedis jedis = jedisClient.getJedis();
		try {
			// 存入
			flag = jedis.exists(JedisClient.serialize(key));
		} catch (Exception e) {
			if (e.getMessage() == null) {
				logger.error("redis数据操作工具类-存入异常:参数无效!key:" + key);
			} else {
				logger.error("redis数据操作工具类-存入异常:" + e.getMessage());
			}
		} finally {
			// 释放资源
			jedisClient.returnResourceObject(jedis);
		}
		return flag;
	}


}
