package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:
 *
 * @author xuqiangsheng
 * @date 2020/11/18 13:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {
    @Autowired
    private PageService pageService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testGetPageHtml(){
        String pageHtml = pageService.getPageHtmlByPageId("5a92141cb00ffc5a448ff1a0");
        logger.info("pageHtml:{}",pageHtml);
    }
}
