package org.gruadtionproject.exception;

import org.gruadtionproject.dto.SeckillExecution;

//关闭
public class SeckillClosedException extends SeckillException {
    public SeckillClosedException(String message) {
        super(message);
    }

    public SeckillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
