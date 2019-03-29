package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/unreadList")
public class UnreadListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtils.getJedis();
        String hget = jedis.hget("unread", (String) request.getAttribute("uid"));
        // 若有未读消息
        if (hget != null) {
            jedis.hdel("unread", (String) request.getAttribute("uid"));
        } else {
            hget = "empty";
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(hget));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
