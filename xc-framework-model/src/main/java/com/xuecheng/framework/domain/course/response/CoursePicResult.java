package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/20.
 */
@Data
@ToString
public class CoursePicResult extends ResponseResult {
    public CoursePicResult(ResultCode resultCode, String pic) {
        super(resultCode);
        this.pic = pic;
    }
    private String pic;

}
