package cn.wangbowen.webchat.service;

public interface GroupService {
    /**
     * 判断是否是群员
     * @param uid
     * @param gid
     * @return
     */
    public boolean isGroupMember(int uid, int gid);

    /**
     * 加群
     * @param uid
     * @param gid
     */
    public void addGroupMember(int uid, int gid);
}
