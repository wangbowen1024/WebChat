package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Msg;
import cn.wangbowen.webchat.domain.User;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectFriend")
public class SelectFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String uid = request.getParameter("uid");
        UserService userService = new UserServiceImpl();
        int id;
        Msg msg = new Msg();
        try {
            id = Integer.parseInt(uid);
        } catch (Exception e) {
            msg.setType(0);
            msg.setContent("请输入正确的UID");
            response.getWriter().write(JSON.toJSONString(msg));
            return;
        }
        User user = userService.getUserByUid(id);
        if (user != null) {
            msg.setType(1);
            msg.setContent(user.getProblem());
        } else {
            msg.setType(2);
            msg.setContent("没有这个用户");
        }
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
