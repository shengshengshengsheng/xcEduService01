package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
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

    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachplanList(courseId);
    }

    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody  Teachplan teachplan) {

        return courseService.addTeachplan(teachplan);
    }


}
