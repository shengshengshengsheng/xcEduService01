package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author shengsheng
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    /**
     * 页面查询
     * @param page 页码
     * @param size 每页记录数
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 添加页面
     * @author shengsheng
     * @date 2020/11/17 9:16
     * @param cmsPage
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    @ApiOperation("添加页面")
    CmsPageResult add(CmsPage cmsPage);

    /**
     * 通过id查询页面信息
     * @author shengsheng
     * @date 2020/11/17 13:01
     * @param id 主键id
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    @ApiOperation("通过ID查询页面")
    CmsPage findById(String id);

    /**
     * 修改页面
     * @author shengsheng
     * @date 2020/11/17 13:05
     * @param id 主键id
     * @param cmsPage
     * @return com.xuecheng.framework.domain.cms.response.CmsPageResult
    */
    @ApiOperation("修改页面")
    CmsPageResult edit(String id, CmsPage cmsPage);

    /**
     * 删除页面
     * @author shengsheng
     * @date 2020/11/17 15:14
     * @param id 主键id
     * @return com.xuecheng.framework.model.response.ResponseResult
    */
    @ApiOperation("删除页面")
    ResponseResult delete(String id);
}
