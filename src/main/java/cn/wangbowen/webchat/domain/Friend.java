package cn.wangbowen.webchat.domain;

/**
 * 好友类
 */
public class Friend {
    private int uid;
    private int fuid;
    private String nickname;
    private String note;
    private int cid;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFuid() {
        return fuid;
    }

    public void setFuid(int fuid) {
        this.fuid = fuid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "uid=" + uid +
                ", fuid=" + fuid +
                ", nickname='" + nickname + '\'' +
                ", note='" + note + '\'' +
                ", cid=" + cid +
                '}';
    }
}
