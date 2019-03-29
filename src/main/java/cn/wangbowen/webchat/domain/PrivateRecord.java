package cn.wangbowen.webchat.domain;

import java.sql.Timestamp;

/**
 * 私聊记录
 */
public class PrivateRecord {
    private int id;
    private int uid;
    private int tuid;
    private Timestamp sendtime;
    private String content;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTuid() {
        return tuid;
    }

    public void setTuid(int tuid) {
        this.tuid = tuid;
    }

    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PrivateRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", tuid=" + tuid +
                ", sendtime=" + sendtime +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
