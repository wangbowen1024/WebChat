package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Friend;
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

@WebServlet("/addFriend")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Msg msg = new Msg();
        response.setContentType("application/json;charset=utf-8");
        try {
            String fuid = request.getParameter("fuid");
            String answer = request.getParameter("answer");
            String cid = request.getParameter("cid");
            String uid = (String) request.getAttribute("uid");
            System.out.println("接受到的CID:" + cid);
            UserService userService = new UserServiceImpl();
            Friend f = userService.checkFriend(Integer.parseInt(uid), Integer.parseInt(fuid));
            if (f != null) {
                msg.setType(-1);
                msg.setContent("对方已经是您好友");
                response.getWriter().write(JSON.toJSONString(msg));
                return;
            }
            User friend = userService.getUserByUid(Integer.parseInt(fuid));
            if (friend != null) {
                if (friend.getAnswer().equals(answer)) {
                    userService.addFriend(Integer.parseInt(uid), Integer.parseInt(fuid), Integer.parseInt(cid));
                    msg.setType(1);
                    msg.setContent("添加成功");
                    response.getWriter().write(JSON.toJSONString(msg));
                    return;
                }
            }
        } catch (Exception e) {
            msg.setType(0);
            msg.setContent("添加失败");
            response.getWriter().write(JSON.toJSONString(msg));
        }
        msg.setType(0);
        msg.setContent("添加失败");
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
