package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收页面查询的查询条件
 * @author Administrator
 **/
@Data
public class QueryPageRequest {
    /**
     * 站点id
     */
    @ApiModelProperty("站点id")
    private String siteId;
    /**
     * 页面ID
     */
    @ApiModelProperty("页面id")
    private String pageId;
    /**
     * 页面名称
     */
    @ApiModelProperty("页面名称")
    private String pageName;
    /**
     * 别名
     */
    @ApiModelProperty("别名")
    private String pageAliase;
    /**
     * 模版id
     */
    @ApiModelProperty("模版id")
    private String templateId;

    /**
     * 页面类型
     */
    @ApiModelProperty("页面类型")
    private String pageType;
}
