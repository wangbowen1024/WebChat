package cn.wangbowen.webchat.web.servlet;

import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.PrivateRecord;
import cn.wangbowen.webchat.service.RecordService;
import cn.wangbowen.webchat.service.UserService;
import cn.wangbowen.webchat.service.impl.RecordServiceImpl;
import cn.wangbowen.webchat.service.impl.UserServiceImpl;
import cn.wangbowen.webchat.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{uid}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<String, Session>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String uid;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("uid") String uid, Session session){
        this.session = session;
        this.uid = uid;
        webSocketMap.put(this.uid, this.session);     //加入map中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        /*  try {
            session.getBasicRemote().sendText("欢迎来到公告聊天室！");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*for (String id : webSocketMap.keySet()) {
            System.out.println("OPEN:" + id + webSocketMap.get(id));
        }*/
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketMap.remove(this.uid);  //从map中删除*/
        subOnlineCount();               //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
       /* for (String id : webSocketMap.keySet()) {
            System.out.println("CLOSE:" + id + webSocketMap.get(id));
        }*/
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        // 格式：类型，ID，TID，内容
        // {type:'g',nickanme:'BOW',uid:'1',giu:'1',content:'你好'}
        // 如果是群聊
        if (message.startsWith("{type:'g'")) {
            JSONObject parse = (JSONObject) JSON.parse(message);
            StringBuilder sb = new StringBuilder();
            sb.append("g");
            sb.append("," + parse.getString("nickname"));
            sb.append("," + parse.getInteger("uid"));
            sb.append("," + parse.getInteger("gid"));
            sb.append("," + parse.getString("content"));
            // 群发消息
            for (String id : webSocketMap.keySet()) {
                try {
                    if (!id.equals(this.uid)) {
                        sendMessage(webSocketMap.get(id), sb.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            // 存表
            GroupRecord gr = JSON.parseObject(message, GroupRecord.class);
            RecordService service = new RecordServiceImpl();
            service.saveGroupRecord(gr);
        } else if (message.startsWith("{type:'p'")){
            // 私聊消息
            // {type:'p',nickanme:'BOW',uid:'1',tuid:'1',content:'你好'}
            JSONObject parse = (JSONObject) JSON.parse(message);
            // 存表
            PrivateRecord pr = JSON.parseObject(message, PrivateRecord.class);
            RecordService service = new RecordServiceImpl();
            service.savePrivateRecord(pr);
            // 如果用户在线
            if (webSocketMap.containsKey(parse.containsKey("tuid"))) {
                System.out.println("在线");
                // 发送
            } else {
                // 不在线，存入未读消息队列redis（map:string,int）
                Jedis jedis = JedisUtils.getJedis();
                Boolean hexists = jedis.hexists("unread", parse.getString("tuid"));
                int count = 1;
                if (hexists) {
                    // 如果有
                    String hget = jedis.hget("unread", parse.getString("tuid"));
                    count = Integer.parseInt(hget) + 1;
                }
                jedis.hset("unread", parse.getString("tuid"), String.valueOf(count));
                // 关闭资源
                JedisUtils.close(jedis);
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException{
        session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}
