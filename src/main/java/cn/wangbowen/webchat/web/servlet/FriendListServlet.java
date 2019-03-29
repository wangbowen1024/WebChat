package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Friend;
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
import java.util.List;

@WebServlet("/friendList")
public class FriendListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl();
        List<Friend> friends = service.findFriends(Integer.parseInt((String) request.getAttribute("uid")));
        if (friends != null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(friends));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
