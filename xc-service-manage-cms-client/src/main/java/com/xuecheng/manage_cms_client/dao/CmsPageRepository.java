package com.xuecheng.manage_cms_client.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author xuqiangsheng
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    /**
     * 根据页面名称查询页面信息
     * @param pageName 页面名称
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据页面名称、站点Id、页面webpath查询
     * @param pageName 页面名称
     * @param siteId 站点id
     * @param pageWebPath web路径
     * @return
     */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);
}
