package com.wugq.blog.config.websocket;

import com.alibaba.fastjson.JSONObject;
import com.wugq.blog.service.ArticleService;
import com.wugq.blog.util.DateUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{uid}/{username}")
@Component
public class WebSocketServer {

    public static ArticleService articleService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<Integer,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private Integer uid = 0;
    private String username = "";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("uid")Integer uid, @PathParam("username") String username) throws IOException {
        this.session = session;
        this.uid = uid;
        this.username = username;
        if(webSocketMap.containsKey(uid)) {
            WebSocketServer old = webSocketMap.get(uid);
            old.handClose();
            webSocketMap.remove(uid);
        } else {
            addOnlineCount();
            sendNewUser();
            System.out.println(uid + ":有新连接加入！当前在线人数为" + getOnlineCount());
        }
        webSocketMap.put(uid,this);
        try {
            sendOtherUsers();
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(CloseReason closeReason) {
        if(closeReason.getCloseCode().getCode() == CloseCode.HAND_CLOSE.getCode()) {
            System.out.println("关闭重复连接！");
        } else {
            webSocketMap.remove(this.uid);
            subOnlineCount();
            webSocketMap.forEach((uid, user) -> {
                Map<String,String> newUser = new HashMap<>();
                newUser.put("quit",this.username);
                try {
                    user.sendMessage(JSONObject.toJSONString(newUser));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(uid + ":有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(uid + "来自客户端的消息:" + message);
        JSONObject mObject = JSONObject.parseObject(message);
        this.sendToAll(mObject.get("content").toString());
    }

    /**
     * 发生错误时调用
     @OnError
     */
     @OnError
     public void onError(Session session, Throwable error) {
         System.out.println(uid + ":发生错误");
         error.printStackTrace();
     }

     private void sendOtherUsers() throws IOException {
         Map<String,List> otherUser = new HashMap<>();
         List<String> users = new ArrayList<>();
         webSocketMap.forEach((uid, user) -> {
             if (uid != this.uid) {
                 users.add(user.username);
             }
         });
         otherUser.put("other",users);
         sendMessage(JSONObject.toJSONString(otherUser));

     }

    private void sendNewUser() throws IOException {
        webSocketMap.forEach((uid, user) -> {
            if (uid != this.uid) {
                try {
                    Map<String,String> newUser = new HashMap<>();
                    newUser.put("new",this.username);
                    user.sendMessage(JSONObject.toJSONString(newUser));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


     public void sendMessage(String message) throws IOException {
         this.session.getBasicRemote().sendText(message);
         //this.session.getAsyncRemote().sendText(message);
     }

     /**
      * 群发自定义消息
      * */
    public void sendToAll(String message) {
        webSocketMap.forEach((uid, user) -> {
            try {
                if(uid != this.uid) {
                    Map<String,Object> msg = new HashMap<>();
                    Map<String,Object> o = new HashMap<>();
                    msg.put("message",o);
                    o.put("isMine",false);
                    o.put("username",this.username);
                    o.put("content",message);
                    o.put("time", DateUtil.dateToString(new Date(),DateUtil.DEFAULT_TIME_FORMAT));
                    user.sendMessage(JSONObject.toJSONString(msg));
                }
            } catch (IOException e) {
                System.out.println("群发信息出错！");
            }
        });
    }

    private void handClose(){
        try {
            this.session.close(new CloseReason(CloseCode.HAND_CLOSE,""));
        } catch (IOException e) {
            System.out.println("手动关闭出错！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    private enum CloseCode implements CloseReason.CloseCode {

        HAND_CLOSE(6000);

        private int code;

        private CloseCode(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return this.code;
        }
    }

}
