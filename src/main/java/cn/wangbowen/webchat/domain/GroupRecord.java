package cn.wangbowen.webchat.domain;

import java.sql.Timestamp;

/**
 * 群聊记录
 */
public class GroupRecord {
    private int id;
    private int uid;
    private int gid;
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

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
        return "GroupRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                ", sendtime=" + sendtime +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
