package cn.wangbowen.webchat.service.impl;

import cn.wangbowen.webchat.dao.UserDao;
import cn.wangbowen.webchat.dao.impl.UserDaoImpl;
import cn.wangbowen.webchat.domain.Friend;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.User;
import cn.wangbowen.webchat.service.GroupService;
import cn.wangbowen.webchat.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public User userInfo(String username) {
        return userDao.userInfo(username);
    }

    @Override
    public List<Friend> findFriends(int uid) {
        return userDao.findFriends(uid);
    }



    @Override
    public int addUser(User user) {
        // 查询用户是否存在
        User user1 = userInfo(user.getUsername());
        if (user1 == null) {
            // 不存在，添加用户
            int row = userDao.addUser(user);
            System.out.println("row:" + row);
            // 同时加公公群
            GroupService groupService = new GroupServiceImpl();
            user1 = userInfo(user.getUsername());
            groupService.addGroupMember(user1.getUid(), 1);
            return row;
        }
        return 0;
    }

    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    public void addFriend(int uid, int fuid,int cid) {
        userDao.addFriend(uid, fuid, cid);
        userDao.addFriend(fuid, uid, cid);
    }

    @Override
    public Friend checkFriend(int uid, int fuid) {
        return userDao.getFriendByTwoId(uid, fuid);
    }

}
