package com.lock.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;

public class JedisClient {

	private JedisPool pool;

	private Integer db = 0;

	/**
	 * 获取Jedis对象
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		return jedis;
	}

	/**
	 * 放回Jedis对象
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnResourceObject(Jedis jedis) {
		pool.returnResourceObject(jedis);
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public void setDb(Integer db) {
		this.db = db;
	}

	public static <T> byte[] serialize(T o) {
		try {
			if (o instanceof String) {
				String str = (String) o;
				return str.getBytes();
			} else {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				ObjectOutputStream s = new ObjectOutputStream(stream);
				s.writeObject(o);
				s.flush();
				s.close();
				return stream.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] bytes) {
		try {
			InputStream in = new ByteArrayInputStream(bytes);
			ObjectInputStream is = new ObjectInputStream(in);
			return (T) is.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


}
