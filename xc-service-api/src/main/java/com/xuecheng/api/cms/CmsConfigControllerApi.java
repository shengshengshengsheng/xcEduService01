package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuqiangsheng
 */
@Api(value="cms配置管理接口",description = "cms配置管理接口，提供数据模型的管理、查询接口")
public interface CmsConfigControllerApi {

    /**
     * 根据id查询CMS配置信息
     * @author XuQiangsheng
     * @date 2020/11/18 8:21
     * @param id 主键id
     * @return com.xuecheng.framework.domain.cms.CmsConfig
    */
    @ApiOperation("根据id查询CMS配置信息")
    CmsConfig getModel(String id);
}
