package cn.wangbowen.webchat.dao.impl;

import cn.wangbowen.webchat.dao.GroupDao;
import cn.wangbowen.webchat.domain.GroupMember;
import cn.wangbowen.webchat.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class GroupDaoImpl implements GroupDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public GroupMember getGroupMember(int uid, int gid) {
        String sql = "select * from groupmembers where uid = ? and gid = ?";
        GroupMember groupMember = template.queryForObject(sql, new BeanPropertyRowMapper<GroupMember>(GroupMember.class), uid, gid);
        return groupMember;
    }

    @Override
    public void addGroupMember(int uid, int gid) {
        String sql = "insert into groupmembers(uid, gid) values(?,?)";
        template.update(sql, uid, gid);
    }
}
