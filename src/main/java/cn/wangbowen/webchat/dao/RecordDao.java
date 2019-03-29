package cn.wangbowen.webchat.dao;

import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.PrivateRecord;

import java.util.List;

public interface RecordDao {
    /**
     * 查找群聊记录
     * @param gid
     * @return
     */
    public List<GroupRecord> getGroupRecord(int gid);

    /**
     * 保存聊天记录
     * @param gr
     */
    public void saveGroupRecord(GroupRecord gr);

    /**
     * 保存私聊聊天记录
     * @param pr
     */
    public void savePrivateRecord(PrivateRecord pr);

    /**
     * 获取私聊记录
     * @param uid
     * @param fuid
     * @return
     */
    public List<PrivateRecord> getPrivateRecord(int uid, int fuid);
}
