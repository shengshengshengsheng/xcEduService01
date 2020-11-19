package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuqiangsheng
 */
public interface CategoryControllerApi {

    /**
     * 查询课程分类信息
     * @author XuQiangsheng
     * @date 2020/11/19 15:32
     * @param
     * @return com.xuecheng.framework.domain.course.ext.CategoryNode
     */
    @ApiOperation("课程分类")
    CategoryNode findCategoryList();
}
