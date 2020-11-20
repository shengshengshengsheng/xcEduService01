package com.xuecheng.manage_course.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuecheng.framework.domain.cms.response.CoursePreviewResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CourseCode;
import com.xuecheng.framework.domain.course.response.CoursePicResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class CourseService {

    @Autowired
    TeachplanRepository teachplanRepository;

    @Autowired
    CourseBaseRepository courseBaseRepository;

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    private CoursePicRepository coursePicRepository;

    @Autowired
    private CourseMarketRepository courseMarketRepository;

    //课程计划查询
    public TeachplanNode findTeachplanList(String courseId){
        return teachplanMapper.selectList(courseId);
    }

    //添加课程计划
    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        if(teachplan == null ||
                StringUtils.isEmpty(teachplan.getCourseid()) ||
                StringUtils.isEmpty(teachplan.getPname())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //课程id
        String courseid = teachplan.getCourseid();
        //页面传入的parentId
        String parentid = teachplan.getParentid();
        if(StringUtils.isEmpty(parentid)){
            //取出该课程的根结点
            parentid = this.getTeachplanRoot(courseid);
        }
        Optional<Teachplan> optional = teachplanRepository.findById(parentid);
        Teachplan parentNode = optional.get();
        //父结点的级别
        String grade = parentNode.getGrade();
        //新结点
        Teachplan teachplanNew = new Teachplan();
        //将页面提交的teachplan信息拷贝到teachplanNew对象中
        BeanUtils.copyProperties(teachplan,teachplanNew);
        teachplanNew.setParentid(parentid);
        teachplanNew.setCourseid(courseid);
        if(grade.equals("1")){
            teachplanNew.setGrade("2");//级别，根据父结点的级别来设置
        }else{
            teachplanNew.setGrade("3");
        }

        teachplanRepository.save(teachplanNew);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    //查询课程的根结点，如果查询不到要自动添加根结点
    private String getTeachplanRoot(String courseId){
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if(!optional.isPresent()){
            return null;
        }
        //课程信息
        CourseBase courseBase = optional.get();
        //查询课程的根结点
        List<Teachplan> teachplanList = teachplanRepository.findByCourseidAndParentid(courseId, "0");
        if(teachplanList == null || teachplanList.size()<=0){
            //查询不到，要自动添加根结点
            Teachplan teachplan = new Teachplan();
            teachplan.setParentid("0");
            teachplan.setGrade("1");
            teachplan.setPname(courseBase.getName());
            teachplan.setCourseid(courseId);
            teachplan.setStatus("0");
            teachplanRepository.save(teachplan);
            return teachplan.getId();
        }
        //返回根结点id
        return teachplanList.get(0).getId();

    }

    public QueryResponseResult findCourseBaseList(Integer page, Integer size, CourseBase courseBase) {
        QueryResult<CourseBase> queryResult = new QueryResult<>();
        CourseBase courseBaseQuery = new CourseBase();
        String name = courseBase.getName();
        if(StringUtils.isNotBlank(name)){
            courseBaseQuery.setName(name);
        }
        ExampleMatcher matcher = ExampleMatcher.matching();
        //名称模糊查询
        matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CourseBase> example = Example.of(courseBaseQuery,matcher);
        Pageable pageable = PageRequest.of(page,size);
        Page<CourseBase> all = this.courseBaseRepository.findAll(example, pageable);
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }


    /**
     * 添加课程基础信息
     * @author XuQiangsheng
     * @date 2020/11/20 8:26
     * @param courseBase
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Transactional
    public ResponseResult addCourseBase(CourseBase courseBase) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<CourseBase> example = Example.of(courseBase,matcher);
        List<CourseBase> all = courseBaseRepository.findAll(example);
        if(!CollectionUtils.isEmpty(all)){
            return new ResponseResult(CommonCode.FAIL);
        }
        courseBase.setId(null);
        //课程状态默认为未发布
        courseBase.setStatus("202001");
        courseBaseRepository.save(courseBase);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 存储课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:38
     * @param courseId
     * @param pic
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    public ResponseResult addCoursePic(String courseId, String pic) {
        CoursePic coursePic = new CoursePic();
        coursePic.setCourseid(courseId);
        coursePic.setPic(pic);
        CoursePic save = coursePicRepository.save(coursePic);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 通过课程id查询课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:50
     * @param courseId
     * @return com.xuecheng.framework.domain.course.response.CoursePicResult
    */
    public CoursePicResult findCoursePicList(String courseId) {
        CoursePic coursePic = coursePicRepository.findById(courseId).orElse(null);
        if(coursePic == null || StringUtils.isBlank(coursePic.getPic())){
            return new CoursePicResult(CommonCode.FAIL,null);
        }
        return new CoursePicResult(CommonCode.SUCCESS,coursePic.getPic());
    }

    /**
     * 删除课程图片
     * @author XuQiangsheng
     * @date 2020/11/20 8:54
     * @param courseId 课程id
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    public ResponseResult deleteCoursePic(String courseId) {
        coursePicRepository.deleteById(courseId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 课程预览
     * @author XuQiangsheng
     * @date 2020/11/20 9:01
     * @param id
     * @return com.xuecheng.framework.domain.cms.response.CoursePreviewResult
    */
    public CoursePreviewResult preview(String id) {
        //TODO 预览课程
        String previewUrl = null;
        return new CoursePreviewResult(CommonCode.SUCCESS,previewUrl);
    }

    public CourseBase getCourseBaseById(String courseId) {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        return optional.orElse(null);
    }

    @Transactional
    public ResponseResult updateCourseBase(String id, CourseBase courseBase) {
        CourseBase one = this.getCourseBaseById(id);
        if(one == null){
            //抛出异常..
            throw new CustomException(CourseCode.BASE_COURSE_IS_NULL);
        }
        //修改课程信息
        one.setName(courseBase.getName());
        one.setMt(courseBase.getMt());
        one.setSt(courseBase.getSt());
        one.setGrade(courseBase.getGrade());
        one.setStudymodel(courseBase.getStudymodel());
        one.setUsers(courseBase.getUsers());
        one.setDescription(courseBase.getDescription());
         courseBaseRepository.save(one);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public CourseMarket getCourseMarketById(String courseId) {
        Optional<CourseMarket> optional = courseMarketRepository.findById(courseId);
        return optional.orElse(null);
    }

    @Transactional
    public CourseMarket updateCourseMarket(String id, CourseMarket courseMarket) {
        CourseMarket one = this.getCourseMarketById(id);
        if(one!=null){
            one.setCharge(courseMarket.getCharge());
            //课程有效期，开始时间
            one.setStartTime(courseMarket.getStartTime());
            //课程有效期，结束时间
            one.setEndTime(courseMarket.getEndTime());
            one.setPrice(courseMarket.getPrice());
            one.setQq(courseMarket.getQq());
            one.setValid(courseMarket.getValid());
            courseMarketRepository.save(one);
        }else{
            //添加课程营销信息
            one = new CourseMarket();
            BeanUtils.copyProperties(courseMarket, one);
            //设置课程id
            one.setId(id);
            courseMarketRepository.save(one);
        }
        return one;
    }
}
