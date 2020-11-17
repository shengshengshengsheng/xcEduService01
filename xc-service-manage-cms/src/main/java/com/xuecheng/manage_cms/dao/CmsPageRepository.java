package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    /**
     * 根据页面名称查询
     * @author shengsheng
     * @date 2020/11/16 14:11
     * @param pageName
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    CmsPage findByPageName(String pageName);
    /**
     * 根据页面名称和类型查询
     * @author shengsheng
     * @date 2020/11/16 14:11
     * @param pageName 页面名称
     * @param pageType 页面类型
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    CmsPage findByPageNameAndPageType(String pageName,String pageType);

    /**
     * 根据页面名称、站点id、页面访问路径查询
     * @author shengsheng
     * @date 2020/11/17 9:19
     * @param pageName 页面名称
     * @param siteId 站点id
     * @param pageWebPath 页面访问路径
     * @return com.xuecheng.framework.domain.cms.CmsPage
    */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

    /**
     * 根据站点和页面类型查询记录数
     * @param siteId 站点id
     * @param pageType 页面类型
     * @return
     */
    int countBySiteIdAndPageType(String siteId,String pageType);

    /**
     * 根据站点和页面类型分页查询
     * @param siteId 站点id
     * @param pageType 页面类型
     * @param pageable 分页参数
     * @return
     */
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);
}
