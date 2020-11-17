package com.xuecheng.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.BeanCopyUtils;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 页面查询方法
     * @param page 页码，从1开始记数
     * @param size 每页记录数
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        if(queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        //条件匹配器 - 模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //别名模糊查询
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //页面名称模糊查询
        exampleMatcher = exampleMatcher.withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值
        CmsPage cmsPage = new CmsPage();
        /*BeanUtils.copyProperties(queryPageRequest,cmsPage);*/
        String siteId = queryPageRequest.getSiteId();
        if(StringUtils.isNotBlank(siteId)){
            cmsPage.setSiteId(siteId);
        }
        String pageType = queryPageRequest.getPageType();
        if(StringUtils.isNotBlank(pageType)){
            cmsPage.setPageType(pageType);
        }
        String pageAliase = queryPageRequest.getPageAliase();
        if(StringUtils.isNotBlank(pageAliase)){
            cmsPage.setPageAliase(pageAliase);
        }
        String pageName = queryPageRequest.getPageName();
        if(StringUtils.isNotBlank(pageName)){
            cmsPage.setPageName(pageName);
        }
        //创建条件实例
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //分页参数
        if(page <=0){
            page = 1;
        }
        //为了适应mongodb的接口将页码减1
        page = page -1;
        if(size<=0){
            size = 10;
        }
        //分页对象
        Pageable pageable = PageRequest.of(page,size);
        //分页查询
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        //数据列表
        queryResult.setList(all.getContent());
        //数据总记录数
        queryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 新增页面
     * @author XuQiangsheng
     * @date 2020/11/17 9:24
     * @param cmsPage
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    public CmsPageResult add(CmsPage cmsPage){
        CmsPage cmsPage1 =
                cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(),
                        cmsPage.getPageWebPath());
        if(cmsPage1 == null){
            //数据库中不存在
            //添加页面主键由spring data 自动生成
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }else {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    /**
     * 通过id查询
     * @author XuQiangsheng
     * @date 2020/11/17 13:04
     * @param id
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    public CmsPage findById(String id) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(id);
        return cmsPageOptional.orElse(null);
    }

    /**
     * 更新用户信息
     * @author XuQiangsheng
     * @date 2020/11/17 13:18
     * @param id 要更新的主键id
     * @param cmsPage 要更新的信息
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    public CmsPageResult update(String id, CmsPage cmsPage) {
        CmsPage cmsPageTemp = this.findById(id);
        if(cmsPageTemp == null){
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        BeanCopyUtils.copyPropertiesIgnoreNull(cmsPageTemp,cmsPage);
        CmsPage save = cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,save);
    }

    /**
     * 删除页面
     * @author XuQiangsheng
     * @date 2020/11/17 15:15
     * @param id
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    public ResponseResult delete(String id) {
        CmsPage cmsPage = this.findById(id);
        if(cmsPage == null){
            return new ResponseResult(CommonCode.FAIL);
        }
        cmsPageRepository.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
