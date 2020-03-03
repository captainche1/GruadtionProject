package org.gruadtionproject.exception;
//禁止重复
public class SeckillRuntimeException extends SeckillException{

    public SeckillRuntimeException(String message) {
        super(message);
    }

    public SeckillRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
