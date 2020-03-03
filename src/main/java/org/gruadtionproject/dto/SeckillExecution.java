package org.gruadtionproject.dto;

import lombok.Data;
import org.gruadtionproject.dict.SeckillStatEnum;
import org.gruadtionproject.entity.SeckillSuccess;

@Data
public class SeckillExecution {

    private long seckillId;

    private int state;

    private String stateInfo;

    private SeckillSuccess seckillSuccess;

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SeckillSuccess seckillSuccess) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getInfo();
        this.seckillSuccess = seckillSuccess;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getInfo();
    }
}
