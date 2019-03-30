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

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String uid = (String) request.getAttribute("uid");
        UserService service = new UserServiceImpl();
        User user = service.getUserByUid(Integer.parseInt(uid));
        response.setContentType("application/json;charset=utf-8");
        Msg msg = new Msg();
        if (user.getPassword().equals(oldPassword)) {
            service.updatePassword(newPassword, Integer.parseInt(uid));
            msg.setType(1);
            msg.setContent("success");
        } else {
            msg.setType(0);
            msg.setContent("failure");
        }
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
