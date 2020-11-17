package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * description:异常抛出类
 *
 * @author shengsheng
 * @date 2020/11/17 15:28
 */
public class ExceptionCast {

    /**
     * 抛出自定义异常
     * @author shengsheng
     * @date 2020/11/17 15:29
     * @param resultCode
     * @return void
    */
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
