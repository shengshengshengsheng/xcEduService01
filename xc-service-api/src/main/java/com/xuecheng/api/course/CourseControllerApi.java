package com.xuecheng.api.course;

import com.xuecheng.framework.domain.cms.response.CoursePreviewResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CoursePicResult;
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


    /**
     * 添加课程基础信息
     * @author XuQiangsheng
     * @date 2020/11/20 8:14
     * @param courseBase
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("添加课程基础信息")
    ResponseResult addCourseBase(CourseBase courseBase);

    /**
     * 保存课程图片地址到课程数据库
     * @author XuQiangsheng
     * @date 2020/11/20 8:30
     * @param courseId
     * @param pic
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("保存课程图片地址到课程数据库")
    ResponseResult addCoursePic(String courseId , String pic);

    /**
     * 查询课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:45
     * @param courseId
     * @return com.xuecheng.framework.domain.course.response.CoursePicResult
    */
    @ApiOperation("查询课程图片")
    CoursePicResult findCoursePicList(String courseId);

    /**
     * 删除课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:51
     * @param courseId
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("删除课程图片")
    ResponseResult deleteCoursePic(String courseId);

    /**
     * 预览课程
     * @author XuQiangsheng
     * @date 2020/11/20 8:59
     * @param id
     * @return com.xuecheng.framework.domain.cms.response.CoursePreviewResult
    */
    @ApiOperation("预览课程")
    CoursePreviewResult preview(String id);

    /**
     * 获取课程基础信息
     * @author XuQiangsheng
     * @date 2020/11/20 10:01
     * @param courseId
     * @return com.xuecheng.framework.domain.course.CourseBase
    */
    @ApiOperation("获取课程基础信息")
    CourseBase getCourseBaseById(String courseId) throws RuntimeException;

    /**
     * 更新课程基础信息
     * @author XuQiangsheng
     * @date 2020/11/20 10:02
     * @param id
     * @param courseBase
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("更新课程基础信息")
    ResponseResult updateCourseBase(String id,CourseBase courseBase);


    /**
     * 获取课程营销信息
     * @author XuQiangsheng
     * @date 2020/11/20 10:18
     * @param courseId
     * @return com.xuecheng.framework.domain.course.CourseMarket
    */
    @ApiOperation("获取课程营销信息")
    public CourseMarket getCourseMarketById(String courseId);

    /**
     * 更新课程营销信息
     * @author XuQiangsheng
     * @date 2020/11/20 10:19
     * @param id
     * @param courseMarket
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("更新课程营销信息")
    public ResponseResult updateCourseMarket(String id,CourseMarket courseMarket);
}