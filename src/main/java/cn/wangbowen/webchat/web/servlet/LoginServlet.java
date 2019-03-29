package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.Msg;
import cn.wangbowen.webchat.domain.User;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import cn.wangbowen.webchat.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Msg msg = new Msg();
        // 验证
        String token = request.getHeader("token");
        if (null != token) {
            // 验证token
            boolean result = JwtUtils.verify(token);
            if (result) {
                msg.setType(2);
                msg.setContent("http://localhost/home.html");
            }
        } else {
            String user = request.getParameter("user");
            JSONObject parse = (JSONObject) JSON.parse(user);
            String username = parse.getString("username");
            String password = parse.getString("password");
            if ("".equals(username) && "".equals(password)) {
                msg.setType(3);
            } else {
                UserService service = new UserServiceImpl();
                User login = service.login(username, password);
                if (login != null) {
                    msg.setType(1);
                    msg.setContent(JwtUtils.sign(login.getUsername(), String.valueOf(login.getUid())));
                } else {
                    msg.setType(0);
                    msg.setContent("用户名或密码错误！");
                }
            }
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(msg));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
