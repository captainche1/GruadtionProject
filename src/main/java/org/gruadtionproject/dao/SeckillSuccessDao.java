package org.gruadtionproject.dao;

import org.apache.ibatis.annotations.Param;
import org.gruadtionproject.entity.SeckillSuccess;

public interface SeckillSuccessDao {
    /**
     * 插入一条详细的购买信息.
     *
     * @param seckillId 秒杀商品的ID
     * @param userUUID  购买用户的手机号码
     * @return 成功插入就返回1, 否则就返回0
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userUUID") long userUUID);

    /**
     * 根据秒杀商品的ID查询明细信息
     *
     * @param seckillId 秒杀商品的ID
     * @param userUUID  购买用户的手机号码
     * @return 秒杀商品的明细信息
     */
    SeckillSuccess queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userUUID") long userUUID);
}
