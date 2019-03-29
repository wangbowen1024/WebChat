package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.PrivateRecord;
import cn.wangbowen.webchat.service.GroupService;
import cn.wangbowen.webchat.service.RecordService;
import cn.wangbowen.webchat.service.impl.GroupServiceImpl;
import cn.wangbowen.webchat.service.impl.RecordServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recordServlet")
public class RecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String uid = (String) request.getAttribute("uid");
        //System.out.println(type + "," + id + "," + uid);
        // 如果是群记录
        if ("g".equals(type)) {
            // 查询群中是否有这个用户
            GroupService groupService = new GroupServiceImpl();
            boolean isGroupMember = groupService.isGroupMember(Integer.parseInt(uid), Integer.parseInt(id));
            if (isGroupMember) {
                RecordService recordService = new RecordServiceImpl();
                List<GroupRecord> recordList = recordService.getGroupRecord(Integer.parseInt(id));
                String s = JSON.toJSONString(recordList);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(s);
                //System.out.println(s);
            }
        } else if ("p".equals(type)) {
            // 私聊记录
            RecordService recordService = new RecordServiceImpl();
            List<PrivateRecord> recordList = recordService.getPrivateRecord(Integer.parseInt(id), Integer.parseInt(uid));
            String s = JSON.toJSONString(recordList);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
