package org.gruadtionproject.service;

import org.gruadtionproject.dto.Exposer;
import org.gruadtionproject.dto.SeckillExecution;
import org.gruadtionproject.entity.Seckill;
import org.gruadtionproject.exception.SeckillClosedException;
import org.gruadtionproject.exception.SeckillException;
import org.gruadtionproject.exception.SeckillRuntimeException;

import java.util.List;

public interface SeckillService {

    //查询所有记录
    List<Seckill> getSeckillList();

    //查询单个记录
    Seckill getById(long seckillId);

    //秒杀开始时输出秒杀接口地址
    //否则输出系统时间与秒杀时间
    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckill, long userUUID, String md5)
        throws SeckillException,SeckillRuntimeException,SeckillClosedException;
}
