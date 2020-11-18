package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 17:24
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size, QueryPageRequest queryPageRequest) {
        return pageService.findList(page,size,queryPageRequest);
    }

    /**
     * 新增页面
     * @author shengsheng
     * @date 2020/11/17 9:25
     * @param cmsPage
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    @Override
    @GetMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    /**
     * 通过id查询页面信息
     * @author shengsheng
     * @date 2020/11/17 13:01
     * @param id 主键id
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.findById(id);
    }

    /**
     * 修改页面信息
     * @author shengsheng
     * @date 2020/11/17 13:06
     * @param id 主键id
     * @param cmsPage 要修改的信息
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.update(id,cmsPage);
    }

    /**
     * 删除页面
     * @author shengsheng
     * @date 2020/11/17 15:14
     * @param id
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") String id) {
        return pageService.delete(id);
    }

    /**
     * 发布页面
     * @author XuQiangsheng
     * @date 2020/11/18 17:51
     * @param pageId
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @Override
    @PostMapping("/postPage/{pageId}")
    public ResponseResult post(@PathVariable("pageId") String pageId) {
        return pageService.postPage(pageId);
    }
}
