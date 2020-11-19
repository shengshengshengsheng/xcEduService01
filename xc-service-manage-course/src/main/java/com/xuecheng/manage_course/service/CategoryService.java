package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/19 17:50
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public CategoryNode findCategoryList() {
        return categoryMapper.findCategoryList();
    }
}
