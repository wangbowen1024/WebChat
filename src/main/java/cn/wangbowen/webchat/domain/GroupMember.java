package cn.wangbowen.webchat.domain;

/**
 * 群员信息类
 */
public class GroupMember {
    private int uid;
    private int gid;

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

    @Override
    public String toString() {
        return "GroupMember{" +
                "uid=" + uid +
                ", gid=" + gid +
                '}';
    }
}
