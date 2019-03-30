package cn.wangbowen.webchat.service;

import cn.wangbowen.webchat.domain.Friend;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.User;

import java.util.List;

/**
 * 用户业务逻辑
 */
public interface UserService {
    /**
     * 登陆
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
     * 根据用户ID查找用户
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
     * 检查是否已经是好友
     * @param uid
     * @param fuid
     * @return
     */
    public Friend checkFriend(int uid, int fuid);

    /**
     * 保存图片
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
     * 修改密码
     * @param newPassword
     * @param uid
     */
    public void updatePassword(String newPassword, int uid);
}
