package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 8:23
 */
@Service
public class CmsConfigService {
    @Autowired
    private CmsConfigRepository cmsConfigRepository;
    /**
     * 根据id查询配置信息
     * @author XuQiangsheng
     * @date 2020/11/18 8:25
     * @param id
     * @return com.xuecheng.framework.domain.cms.CmsConfig
    */
    public CmsConfig getConfigById(String id){
        Optional<CmsConfig> cmsConfigOptional = cmsConfigRepository.findById(id);
        return cmsConfigOptional.orElse(null);
    }
}
