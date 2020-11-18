package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.PageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 14:09
 */
@Controller
public class CmsPagePreviewController extends BaseController {
    @Autowired
    private PageService pageService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 页面预览
     * @author XuQiangsheng
     * @date 2020/11/18 14:36
     * @param pageId 页面id
     * @return void
    */
    @GetMapping("/cms/preview/{pageId}")
    public void preview(@PathVariable("pageId")String pageId){
        String pageHtml = pageService.getPageHtmlByPageId(pageId);
        if(StringUtils.isBlank(pageHtml)){
            return;
        }
        try{
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pageHtml.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            logger.error("preview error:",e);
        }
    }

}
