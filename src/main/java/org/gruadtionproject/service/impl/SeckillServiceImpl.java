package org.gruadtionproject.service.impl;

import org.gruadtionproject.dao.SeckillDao;
import org.gruadtionproject.dao.SeckillSuccessDao;
import org.gruadtionproject.dict.SeckillStatEnum;
import org.gruadtionproject.dto.Exposer;
import org.gruadtionproject.dto.SeckillExecution;
import org.gruadtionproject.entity.Seckill;
import org.gruadtionproject.entity.SeckillSuccess;
import org.gruadtionproject.exception.SeckillClosedException;
import org.gruadtionproject.exception.SeckillException;
import org.gruadtionproject.exception.SeckillRuntimeException;
import org.gruadtionproject.service.SeckillService;
import org.gruadtionproject.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillSuccessDao seckillSuccessDao;

    //混淆md5
    private final String slat = "Hnust123";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        LocalDateTime startTime = seckill.getStartTime();
        LocalDateTime endTime = seckill.getEndTime();
        LocalDateTime nowTime = LocalDateTime.now();
        //   开始时间大于现在的时候说明没有开始秒杀活动    秒杀活动结束时间小于现在的时间说明秒杀已经结束了
        if (nowTime.isAfter(endTime) || nowTime.isBefore(startTime)) {
            logger.info("未开启秒杀");
            return new Exposer(false, seckillId, nowTime, startTime, endTime);
        }
        String md5 = MD5Util.getMD5(seckillId, slat);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userUUID, String md5) throws SeckillException, SeckillRuntimeException, SeckillClosedException {
        if (md5 == null || !md5.equals(MD5Util.getMD5(seckillId, slat))) {
            throw new SeckillException("数据异常，请重试");
        }
        try {
            //执行逻辑:减库存加记录
            int updateCount = seckillDao.reduceStock(seckillId, LocalDateTime.now());
            if (updateCount <= 0) {
                //没有更新到记录
                throw new SeckillClosedException("本场已被抢完");
            } else {
                int insertCount = seckillSuccessDao.insertSuccessKilled(seckillId, userUUID);
                if (insertCount <= 0) {
                    throw new SeckillRuntimeException("重复秒杀");
                } else {
                    SeckillSuccess seckillSuccess = seckillSuccessDao.queryByIdWithSeckill(seckillId, userUUID);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, seckillSuccess);
                }
            }
        } catch (SeckillClosedException | SeckillRuntimeException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("未知错误");
        }
    }

}
