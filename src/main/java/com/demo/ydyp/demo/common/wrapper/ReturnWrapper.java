package com.demo.ydyp.demo.common.wrapper;

import lombok.Data;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 17:00
 * @Description:用于返回值约定
 */
@Data
public class ReturnWrapper<T> {

    //成功返回状态码200
    final static Integer CODE_SUCCESS = 200;

    //失败返回状态码500
    final static Integer CODE_FAILURE = 500;

    //成功返回字符串“操作成功”
    final static String MESSAGE_SUCCESS = "操作成功";

    //失败返回字符串“操作失败”
    final static String MESSAGE_FAILURE = "操作失败";

    private Integer code;

    private String message;

    private T data;

    public  ReturnWrapper() {

    }

    public ReturnWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //返回成功data数据，message自定义
    public  <T>ReturnWrapper<T> success(T data,String message){
        return  new ReturnWrapper<>(CODE_SUCCESS,message,data);
    }

    //返回成功data，message操作成功
    public  <T>ReturnWrapper<T> successWithData(T data){
        return new ReturnWrapper<>(CODE_SUCCESS,MESSAGE_SUCCESS,data);
    }

    //返回操作成功，不包含data,自定义message
    public  <T>ReturnWrapper<T> successWithMessage(String message){
        return new ReturnWrapper<>(CODE_SUCCESS,message,null);
    }

    //返回操作成功，不包含data，message操作成功
    public  <T>ReturnWrapper<T> success(){
        return new ReturnWrapper<>(CODE_SUCCESS,MESSAGE_SUCCESS,null);
    }




    //返回失败data数据，message自定义
    public  <T>ReturnWrapper<T> failure(T data,String message){
        return  new ReturnWrapper<>(CODE_FAILURE,message,data);
    }

    //返回失败data，message操作失败
    public  <T>ReturnWrapper<T> failureWithData(T data){
        return new ReturnWrapper<>(CODE_FAILURE,MESSAGE_FAILURE,data);
    }

    //返回操作失败，不包含data,自定义message
    public  <T>ReturnWrapper<T> failureWithMessage(String message){
        return new ReturnWrapper<>(CODE_FAILURE,message,null);
    }

    //返回操作失败，不包含data，message操作失败
    public  <T>ReturnWrapper<T> failure(){
        return new ReturnWrapper<>(CODE_FAILURE,MESSAGE_FAILURE,null);
    }

    //判断是否成功
    public boolean isSuccess(){
        return this.code.equals(CODE_SUCCESS);
    }

}
