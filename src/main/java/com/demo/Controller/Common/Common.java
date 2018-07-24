package com.demo.Controller.Common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/Common")
public class Common {

    Logger logger = LoggerFactory.getLogger(Common.class);
    @Value("${file.path}")
    private String filePath;
    // 单文件上传
    //multipart/form-data
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {

        try {
            if (file==null||file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            //String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //logger.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String path = filePath + fileName; //+ suffixName;
            File upload = new File(path);
            // 检测是否存在目录
            if (!upload.getParentFile().exists()) {
                upload.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(upload);// 文件写入
            return "上传成功";

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //多文件上传
    @RequestMapping(value = "/multipartUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multipartUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            //String filePath = "D://aim//";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }
    //文件下载相关代码
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            //String realPath = "D://aim//";
            File file = new File(filePath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
