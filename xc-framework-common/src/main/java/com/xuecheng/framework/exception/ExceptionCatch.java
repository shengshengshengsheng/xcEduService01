package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:异常捕捉类
 *
 * @author xuqiangsheng
 * @date 2020/11/17 15:30
 */
@ControllerAdvice
public class ExceptionCatch {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
     */
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    /**
     * 使用builder来构建一个异常类型和错误代码的异常
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder =
            ImmutableMap.builder();
    /**
     * 捕捉自定义CustomException异常
     * @author XuQiangsheng
     * @date 2020/11/17 15:35
     * @param e 异常信息
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ExceptionHandler
    @ResponseBody
    public ResponseResult customException(CustomException e){
        logger.info("catch exception:{} \r\n exception: ",e.getMessage(),e);
        ResultCode resultCode = e.getResultCode();
        return new ResponseResult(resultCode);
    }

    /**
     * 捕获Exception异常
     * @author XuQiangsheng
     * @date 2020/11/17 15:48
     * @param e
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ExceptionHandler
    @ResponseBody
    public ResponseResult exception(Exception e){
        //打印日志
        logger.info("catch exception:{} \r\n exception",e.getMessage(),e);
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();
        }
        final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        final ResponseResult responseResult;
        if (resultCode != null) {
            responseResult = new ResponseResult(resultCode);
        } else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }
    static {
        //在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }
}
