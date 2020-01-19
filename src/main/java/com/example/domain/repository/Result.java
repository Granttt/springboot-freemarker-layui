package com.example.domain.repository;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/10 23:58
 * @Version: 1.0
 * https://blog.csdn.net/byteArr/article/details/80955703
 */
public class Result<T> {
    //状态码：1001-成功
    private int code;
    //返回信息
    private String msg;
    //数据是否正常请求
    private boolean success;
    //具体返回的数据
    private T detail;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}