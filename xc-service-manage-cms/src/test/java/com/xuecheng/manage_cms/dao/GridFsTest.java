package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;

    /**
     * GridFS存储文件测试
     * @author XuQiangsheng
     * @date 2020/11/18 12:48
     * @param
     * @return void
     */
    @Test
    public void testGridFs() throws FileNotFoundException {
        File file = new File("E:\\learning\\xc\\xcEduService01\\genResource\\test1.html");
        FileInputStream inputStream = new FileInputStream(file);
        ObjectId store = gridFsTemplate.store(inputStream, "测试文件","");
        String fileId = store.toString();
        System.out.println(fileId);
    }

    /**
     * GridFS读取文件测试
     * @author XuQiangsheng
     * @date 2020/11/18 12:53
     * @param
     * @return void
    */
    @Test
    public void queryFile() throws IOException {
        String fileId = "5fb4a781ddd43a1b80623c71";
        //根据id查询文件
        GridFSFile gridFSFile =
                gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        //打开下载流对象
        GridFSDownloadStream gridFSDownloadStream =
                gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //获取流中的数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF‐8");
        System.out.println(s);
    }

    /**
     * 删除文件
     * @author XuQiangsheng
     * @date 2020/11/18 12:55
     * @param
     * @return void
    */
    @Test
    public void testDelFile() {
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("5fb4a781ddd43a1b80623c71")));
    }

}