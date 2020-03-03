package org.gruadtionproject.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeckillSuccess {

    private long seckillId;
    /* 用户的唯一标识*/
    private long userUUID;
    /* 秒杀的状态*/
    private short state;
    /* 创建时间*/
    private LocalDateTime createTime;
    /* 多对一，因为一件商品在库存中肯定有许多，对应的购买信息也有很多*/
    private Seckill seckill;

    public SeckillSuccess() {
    }

    public SeckillSuccess(long seckillId, long userUUID, short state, LocalDateTime createTime, Seckill seckill) {
        this.seckillId = seckillId;
        this.userUUID = userUUID;
        this.state = state;
        this.createTime = createTime;
        this.seckill = seckill;
    }
}
