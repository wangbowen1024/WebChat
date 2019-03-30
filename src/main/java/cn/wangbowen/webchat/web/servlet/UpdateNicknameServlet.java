package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Msg;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateNickname")
public class UpdateNicknameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = (String) request.getAttribute("uid");
        String newNickname = request.getParameter("newNickname");
        UserService service = new UserServiceImpl();
        service.updateNickname(newNickname, Integer.parseInt(uid));
        response.setContentType("application/json;charset=utf-8");
        Msg msg = new Msg();
        msg.setType(1);
        msg.setContent("success");
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
