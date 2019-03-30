package cn.wangbowen.webchat.dao.impl;

import cn.wangbowen.webchat.dao.UserDao;
import cn.wangbowen.webchat.domain.Friend;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.User;
import cn.wangbowen.webchat.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User login(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception ex) {
            return user;
        }
        return user;
    }

    @Override
    public User userInfo(String username) {
        String sql = "select * from user where username = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception ex) {
            return user;
        }
        return user;
    }

    @Override
    public List<Friend> findFriends(int uid) {
        String sql = "select f.uid,f.fuid,f.note,f.cid,u.nickname from friend f inner join user u on f.fuid = u.uid and f.uid = ?";
        List<Friend> friends = template.query(sql, new BeanPropertyRowMapper<Friend>(Friend.class), uid);
        return friends;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into user(username,password,nickname,email,problem,answer) values(?,?,?,?,?,?)";
        int row = template.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(),
                user.getProblem(), user.getAnswer());
        return row;
    }

    @Override
    public User getUserByUid(int uid) {
        String sql = "select * from user where uid = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
        } catch (Exception ex) {
            return user;
        }
        return user;
    }

    @Override
    public void addFriend(int uid, int fuid, int cid) {
        String sql = "insert into friend(uid,fuid,cid) values(?,?,?)";
        template.update(sql, uid, fuid, cid);
    }

    @Override
    public Friend getFriendByTwoId(int uid, int fuid) {
        String sql = "select * from friend where uid = ? and fuid = ? limit 0,1";
        Friend friend = null;
        try {
            friend = template.queryForObject(sql, new BeanPropertyRowMapper<Friend>(Friend.class), uid, fuid);
        } catch (Exception e) {
            return friend;
        }
        return friend;
    }

    @Override
    public void saveImg(String fileName, int uid) {
        String sql = "update user set img = ? where uid = ?";
        template.update(sql, fileName, uid);
    }
}
