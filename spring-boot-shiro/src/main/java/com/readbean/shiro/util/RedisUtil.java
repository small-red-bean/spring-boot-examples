package com.readbean.shiro.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static int expireTime = 1800;
    private static JedisPool jedisPool;
    private static int maxActive = 200;
    private static int maxIdle = 200;
    private static long maxWait = 5000;
    private static String password = "qmc20171109";
    private static String host = "172.19.100.210";
    private static int port = 6379;

    static {
        initPool();
    }

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, host, port, 10000, password);
    }

    private static Jedis getJedis() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
        } catch(Exception e){
            e.printStackTrace();
        }
        return jedis;
    }

    private static void recycleJedis(Jedis jedis){
        if(jedis != null){
            try{
                jedis.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void setObject(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        if(jedis != null) {
            if(!jedis.exists(key)){
                jedis.set(key, value);
            }
            jedis.expire(key, expireTime);
        }
    }

    public static byte[] getObject(byte[] key){
        Jedis jedis = getJedis();
        byte[] bytes = null;
        if(jedis != null){
            try{
                bytes = jedis.get(key);;
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                recycleJedis(jedis);
            }
        }
        return bytes;

    }

    public static void updateObject(byte[] key){
        Jedis jedis = getJedis();
        if(jedis != null){
            try{
                jedis.expire(key, expireTime);
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                recycleJedis(jedis);
            }
        }

    }

    public static void removeObject(byte[] key) {
        Jedis jedis = getJedis();
        if(jedis != null){
            try{
                jedis.decr(key);
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                recycleJedis(jedis);
            }
        }
    }
}
