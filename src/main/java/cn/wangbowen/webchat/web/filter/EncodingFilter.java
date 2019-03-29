package cn.wangbowen.webchat.web.filter;

import cn.wangbowen.webchat.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 强转
        HttpServletRequest request = (HttpServletRequest) req;
        // 获取URI
        String uri = request.getRequestURI();
        //System.out.println("URI:" + uri);

        // 判断自愿路径，注意放CSS等资源
        if (uri.contains("/css/") || uri.contains("/js/") || uri.contains("/img/") || uri.contains("html")
        || uri.contains("/register") || uri.contains("/websocket") || uri.equals("/") || uri.contains("ico")
        || uri.contains("/login")) {
            //System.out.println("直接放行：" + uri);
            chain.doFilter(request, resp);
            return;
        } else {
            // 验证
            String token = request.getHeader("token");
            if (null != token) {
                // 验证token
                boolean result = JwtUtils.verify(token);
                if (result) {
                    // 设置用户名
                    req.setAttribute("username", JwtUtils.getUsername(token));
                    req.setAttribute("uid", JwtUtils.getUserid(token));
                    chain.doFilter(req, resp);
                    //System.out.println("放行uri:" + uri);
                    return;
                }
            }
            //request.getRequestDispatcher("index.html").forward(request, resp);
        }
        // 跳转登陆界面
        //System.out.println("拦截uri:" + uri);
        ((HttpServletResponse)resp).sendRedirect("http://localhost/index.html");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
