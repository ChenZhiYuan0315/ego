package com.ego.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedis=new JedisPoolConfig();
		jedis.setMaxIdle(20);
		jedis.setMaxTotal(10);
		jedis.setMinIdle(20);
		return jedis;
		
	}
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedis){
		JedisConnectionFactory factory=new JedisConnectionFactory();
		factory.setPoolConfig(jedis);
		factory.setPort(6379);
		factory.setHostName("192.168.137.128");
		return factory;
		
	}
	@Bean
	public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
		RedisTemplate<String,Object> redis=new RedisTemplate<>();
		redis.setConnectionFactory(factory);
		redis.setKeySerializer(new StringRedisSerializer());
		redis.setValueSerializer(new StringRedisSerializer());
		return redis;
		
	}
}
