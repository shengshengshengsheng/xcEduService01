package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuqiangsheng
 */
public interface CourseControllerApi {
    /**
     * 查询基础课程信息列表
     * @author XuQiangsheng
     * @date 2020/11/19 13:22
     * @param page
     * @param size
     * @param courseBase
     * @return java.util.List<com.xuecheng.framework.domain.course.CourseBase>
    */
    @ApiOperation("查询基础课程信息列表")
    QueryResponseResult findCourseBaseList(Integer page, Integer size, CourseBase courseBase);
    /**
     * 课程计划查询
     * @author XuQiangsheng
     * @date 2020/11/19 9:15
     * @param courseId
     * @return com.xuecheng.framework.domain.course.ext.TeachplanNode
    */
    @ApiOperation("课程计划查询")
    TeachplanNode findTeachplanList(String courseId);

    /**
     * 添加课程计划
     * @author XuQiangsheng
     * @date 2020/11/19 13:23
     * @param teachplan
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("添加课程计划")
    ResponseResult addTeachplan(Teachplan teachplan);
}