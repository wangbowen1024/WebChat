package cn.wangbowen.webchat.dao;

import cn.wangbowen.webchat.domain.Friend;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.User;

import java.util.List;

/**
 * UserDao接口
 */
public interface UserDao {
    /**
     * 根据用户名密码登陆
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public User userInfo(String username);

    /**
     * 查找好友列表
     * @param uid
     * @return
     */
    public List<Friend> findFriends(int uid);


    /**
     * 注册用户
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据UID查找用户
     * @param uid
     * @return
     */
    public User getUserByUid(int uid);

    /**
     * 添加好友
     * @param uid
     * @param fuid
     * @param cid
     */
    public void addFriend(int uid, int fuid, int cid);

    /**
     * 查找好友关系
     * @param uid
     * @param fuid
     * @return
     */
    public Friend getFriendByTwoId(int uid, int fuid);

    /**
     * 保存图片名
     * @param fileName
     * @param uid
     */
    public void saveImg(String fileName, int uid);

    /**
     * 更新昵称
     * @param newNickname
     * @param uid
     */
    public void updateNickname(String newNickname, int uid);

    /**
     * 更新密码
     * @param newPassword
     * @param uid
     */
    public void updatePassword(String newPassword, int uid);

}
