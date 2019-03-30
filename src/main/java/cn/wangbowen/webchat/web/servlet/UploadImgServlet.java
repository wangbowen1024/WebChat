package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Msg;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import cn.wangbowen.webchat.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/uploadImg")
@MultipartConfig //表示当前servlet可以处理multipart请求
public class UploadImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Msg msg = new Msg();
        // 验证
        String token = request.getParameter("token");
        if (null != token) {
            // 验证token
            boolean result = JwtUtils.verify(token);
            if (result) {
                String uid = JwtUtils.getUserid(token);
                //获取服务器存放上传文件的路径
                String path = this.getServletContext().getRealPath("/upload");
                System.out.println(path);
                //获取上传文件，photo是html表单中的name
                Part part = request.getPart("photo");
                //获取上传文件的名称，这是servlet3.1中加入的方法
                String fileName = part.getSubmittedFileName();
                int i = fileName.lastIndexOf(".");
                String fileNameEnd = fileName.substring(i);
                fileName = uid + fileNameEnd;
                //文件名存入数据库
                UserService service = new UserServiceImpl();
                service.saveImg(fileName, Integer.parseInt(uid));
                //创建父目录
                File parentDir = new File(path);
                //如果父目录不存在，则创建
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                //将上传的文件写入到指定的服务器路径中
                part.write(path + File.separator + fileName);
                msg.setType(1);
                msg.setContent("上传成功");
            } else {
                msg.setType(0);
                msg.setContent("上传失败");
            }
        } else {
            msg.setType(0);
            msg.setContent("上传失败");
        }
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
