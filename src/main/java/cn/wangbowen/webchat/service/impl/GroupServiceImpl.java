package cn.wangbowen.webchat.service.impl;

import cn.wangbowen.webchat.dao.GroupDao;
import cn.wangbowen.webchat.dao.impl.GroupDaoImpl;
import cn.wangbowen.webchat.domain.GroupMember;
import cn.wangbowen.webchat.service.GroupService;

public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao = new GroupDaoImpl();
    @Override
    public boolean isGroupMember(int uid, int gid) {
        GroupMember groupMember = groupDao.getGroupMember(uid, gid);
        if (groupMember != null) {
            return true;
        }
        return false;
    }

    @Override
    public void addGroupMember(int uid, int gid) {
        groupDao.addGroupMember(uid, gid);
    }
}
