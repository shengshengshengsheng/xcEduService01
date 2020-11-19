package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CategoryControllerApi;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/19 17:49
 */
@RestController
public class CategoryController implements CategoryControllerApi {
    @Autowired
    private CategoryService categoryService;

    /**
     *  查询课程分类信息
     * @author XuQiangsheng
     * @date 2020/11/19 15:33
     * @param
     * @return com.xuecheng.framework.domain.course.ext.CategoryNode
     */
    @Override
    @GetMapping("/category/list")
    public CategoryNode findCategoryList() {
        return categoryService.findCategoryList();
    }
}
