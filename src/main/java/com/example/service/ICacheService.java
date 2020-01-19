package com.example.service;

/**
 * @auther: 高希阳
 * @Date: 2019/1/24 16:53
 * @Description:根据多个教程整合而来
 * -2
 */
public interface ICacheService {

    /**
     * 设置键值
     * @param key
     * @param value
     * @return
     */
    public  boolean set(String key,String value);

    /**
     * 获取键值
     * @param key
     * @return
     */
    public  String get(String key);

    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     *
     * @param key
     * @param increament
     * @return
     */
    public Long incrBy(String key,long increament);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean existKey(String key);
}
