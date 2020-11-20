package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.cms.response.CoursePreviewResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CoursePicResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    @Autowired
    CourseService courseService;

    /**
     * 查询课程列表
     * @author XuQiangsheng
     * @date 2020/11/19 13:23
     * @param page
     * @param size
     * @param courseBase
     * @return java.util.List<com.xuecheng.framework.domain.course.CourseBase>
    */
    @Override
    @GetMapping("coursebase/list/{page}/{size}")
    public QueryResponseResult findCourseBaseList(@PathVariable("page") Integer page,@PathVariable("size") Integer size, CourseBase courseBase) {
        return courseService.findCourseBaseList(page,size,courseBase);
    }

    /**
     *查询课程计划
     * @author XuQiangsheng
     * @date 2020/11/20 8:27
     * @param courseId
     * @return com.xuecheng.framework.domain.course.ext.TeachplanNode
    */
    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    /**
     * 添加课程计划
     * @author XuQiangsheng
     * @date 2020/11/20 8:27
     * @param teachplan
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody  Teachplan teachplan) {

        return courseService.addTeachplan(teachplan);
    }

    /**
     * 添加课程基础信息
     * @author XuQiangsheng
     * @date 2020/11/20 8:14
     * @param courseBase
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @PostMapping("/coursebase/add")
    public ResponseResult addCourseBase(@RequestBody CourseBase courseBase) {
        return courseService.addCourseBase(courseBase);
    }

    /**
     * 保存课程图片地址到课程数据库
     * @author XuQiangsheng
     * @date 2020/11/20 8:33
     * @param courseId
     * @param pic
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @PostMapping("/coursepic/add")
    public ResponseResult addCoursePic(@RequestParam("courseId") String courseId,
                                       @RequestParam("pic") String pic) {
        return courseService.addCoursePic(courseId,pic);
    }

    /**
     * 查询课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:47
     * @param courseId
     * @return com.xuecheng.framework.domain.course.response.CoursePicResult
    */
    @Override
    @GetMapping("/coursepic/list/{courseId}")
    public CoursePicResult findCoursePicList(@PathVariable("courseId") String courseId) {
        return courseService.findCoursePicList(courseId);
    }

    /**
     * 删除课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:54
     * @param courseId
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @DeleteMapping("/coursepic/delete")
    public ResponseResult deleteCoursePic(@RequestParam("courseId") String courseId) {
        return courseService.deleteCoursePic(courseId);
    }

    /**
     * 课程预览
     * @author XuQiangsheng
     * @date 2020/11/20 9:01
     * @param id
     * @return com.xuecheng.framework.domain.cms.response.CoursePreviewResult
    */
    @Override
    @PostMapping("/preview")
    public CoursePreviewResult preview(@RequestParam("id") String id) {
        return courseService.preview(id);
    }

    @Override
    @GetMapping("/coursebase/get/{courseId}")
    public CourseBase getCourseBaseById(@PathVariable("courseId") String courseId) throws
            RuntimeException {
        return courseService.getCourseBaseById(courseId);
    }
    @Override
    @PutMapping("/coursebase/update/{id}")
    public ResponseResult updateCourseBase(@PathVariable("id") String id, @RequestBody CourseBase
            courseBase) {
        return courseService.updateCourseBase(id,courseBase);
    }

    @Override
    @GetMapping("/coursemarket/get/{courseId}")
    public CourseMarket getCourseMarketById(@PathVariable("courseId") String courseId) {
        return courseService.getCourseMarketById(courseId);
    }

    @Override
    @PostMapping("/coursemarket/update/{id}")
    public ResponseResult updateCourseMarket(@PathVariable("id") String id, @RequestBody CourseMarket
            courseMarket) {
        CourseMarket courseMarket_u = courseService.updateCourseMarket(id, courseMarket);
        if(courseMarket_u!=null){
            return new ResponseResult(CommonCode.SUCCESS);
        }else{
            return new ResponseResult(CommonCode.FAIL);
        }
    }
}
