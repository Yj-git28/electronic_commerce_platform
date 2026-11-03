package com.ch.ch11;

// 继承RuntimeException（运行时异常，不需要throws声明）
public class NoLoginException extends RuntimeException {
    public NoLoginException(String message) {
        super(message);
    }
}
