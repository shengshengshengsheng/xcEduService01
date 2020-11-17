package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * description:自定义异常类
 *
 * @author shengsheng
 * @date 2020/11/17 15:25
 */
public class CustomException extends RuntimeException{
    private ResultCode resultCode;
    public CustomException(ResultCode resultCode){
        super("错误代码:"+resultCode.code() + "错误信息:"+ resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
