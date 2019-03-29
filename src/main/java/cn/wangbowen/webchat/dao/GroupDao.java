package cn.wangbowen.webchat.dao;

import cn.wangbowen.webchat.domain.GroupMember;

public interface GroupDao {
    /**
     * 查找是否是群员
     * @param uid
     * @param gid
     * @return
     */
    public GroupMember getGroupMember(int uid, int gid);

    /**
     * 加群
     * @param uid
     * @param gid
     */
    public void addGroupMember(int uid, int gid);
}
