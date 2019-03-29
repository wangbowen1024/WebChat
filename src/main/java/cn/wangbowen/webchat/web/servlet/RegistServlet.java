package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Msg;
import cn.wangbowen.webchat.domain.User;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import cn.wangbowen.webchat.utils.JwtUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registerInfo = request.getParameter("registerInfo");
        User user = JSON.parseObject(registerInfo, User.class);
        UserService service = new UserServiceImpl();
        int i = service.addUser(user);
        Msg msg = new Msg();
        if (i == 0) {
            msg.setType(-1);
            msg.setContent("注册失败，用户名已存在");
        } else {
            msg.setType(1);
            msg.setContent(JwtUtils.sign(user.getUsername(), String.valueOf(user.getUid())));
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(msg));
        //System.out.println(JSON.toJSONString(msg) + "???");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
