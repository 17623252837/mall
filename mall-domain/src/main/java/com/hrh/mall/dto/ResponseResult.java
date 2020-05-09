package com.hrh.mall.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.commons.dto.com.hrh
 * @ClassName: ResponseResult
 * @Author: HuRonghua
 * @Description: 统一的传输对象
 * @Date: 2020/3/19 14:28
 * @Version: 1.0
 */
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 3468352004150968551L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回对象
     */
    private T data;
    public ResponseResult() {
        super();
    }
    public ResponseResult(Integer code) {
        super();
        this.code = code;
    }
    public ResponseResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public ResponseResult(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }
    public ResponseResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }
    public ResponseResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Integer getcode() {
        return code;
    }
    public void setcode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResponseResult<?> other = (ResponseResult<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    /**
     * vue-element-admin 通用状态码
     */
    public class CodeStatus{
        /**
         * 请求成功
         */
        public static final int OK = 20000;

        /**
         * 请求失败
         */
        public static final int FAIL = 20002;

        /**
         * 熔断请求
         */
        public static final int BREAKING = 20004;

        /**
         * 非法请求
         */
        public static final int ILLEGAL_REQUEST = 50000;

        /**
         * 非法令牌
         */
        public static final int ILLEGAL_TOKEN = 50008;

        /**
         * 其他客户登录
         */
        public static final int OTHER_CLIENTS_LOGGED_IN = 50012;

        /**
         * 令牌已过期
         */
        public static final int TOKEN_EXPIRED = 50014;
    }
}
