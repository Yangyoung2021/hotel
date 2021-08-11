package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.young.utils.SystemConstant;
import com.young.utils.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/file")
@RestController
public class FileController {

    /**
     * 文件上传方法
     * @param attach 与jsp页面保持一致
     * @return 返回信息
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile attach){
        //1.创建回显集合
        Map<String, Object> map = new HashMap<String, Object>();
        //2.判断文件是否为空
        if (!attach.isEmpty()){
            //3.不为空，得到原始文件名
            String originalFilename = attach.getOriginalFilename();
            //4.获取源文件的扩展名
            String file_extension = FilenameUtils.getExtension(originalFilename);
            //5.将文件重命名
            String newFileName = UUIDUtils.UUIDRandom() + "." + file_extension;
            //6.创建文件保存父级地址
            String uploadPath = SystemConstant.UPLOAD_PATH;
            //7.将每天的文件作为一个文件夹放置
            String dataPath = new SimpleDateFormat(SystemConstant.DIRECTORY_NAME).format(new Date());
            //8.组装最终文件名
            String finalFileName = dataPath + "/" + newFileName;
            //9.创建文件对象，用来直接保存数据
            File destPath = new File(uploadPath,finalFileName);
            //10.判断该文件夹是否存在，不存在则创建一个
            if (!destPath.getParentFile().exists()){
                boolean mkdirs = destPath.getParentFile().mkdirs();
                System.out.println(mkdirs);
            }
            //11.进行文件上传
            try {
                attach.transferTo(destPath);
                map.put("code",0);//状态码
                map.put("msg","文件上传成功");//文件上传提示信息
                Map<String,Object> dataMap = new HashMap<String, Object>();
                dataMap.put("src",SystemConstant.VIRTUAL_PATH+finalFileName);//文件数据
                map.put("data",dataMap);
                map.put("imagePath",finalFileName);//隐藏域的值
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(map);
    }

}
