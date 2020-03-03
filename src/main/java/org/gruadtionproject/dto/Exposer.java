package org.gruadtionproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Exposer {

    //是否开启
    private boolean exposed;

    private String md5;

    private long seckillId;

    private LocalDateTime now;

    private LocalDateTime end;

    private LocalDateTime start;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, LocalDateTime now, LocalDateTime start, LocalDateTime end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.end = end;
        this.start = start;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }
}
