package org.gruadtionproject.dao;

import org.apache.ibatis.annotations.Param;
import org.gruadtionproject.entity.Seckill;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SeckillMapper {

    /**
     * 减少库存.
     *
     * @param seckillId 秒杀商品ID
     * @param killTime  秒杀的精确时间
     * @return 如果秒杀成功就返回1，否则就返回0
     */
    int ReduceStock(@Param("seckillId") long seckillId, LocalDateTime killTime);
    /**
     * 查询秒杀商品的详情.
     *
     * @param seckillId 秒杀商品ID
     * @return 对应商品ID的的数据
     */
    Seckill queryById(@Param("seckillId") long seckillId);
    /**
     * 查询秒杀的商品列表.
     *
     * @param offset 偏移量
     * @param limit  限制查询的数据个数
     * @return 符合偏移量查出来的数据个数
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
