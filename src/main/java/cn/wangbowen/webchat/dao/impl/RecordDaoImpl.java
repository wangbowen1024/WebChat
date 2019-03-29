package cn.wangbowen.webchat.dao.impl;

import cn.wangbowen.webchat.dao.RecordDao;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.PrivateRecord;
import cn.wangbowen.webchat.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RecordDaoImpl implements RecordDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<GroupRecord> getGroupRecord(int gid) {
        String sql = "select g.id,g.uid,u.nickname,g.gid,g.sendtime,g.content from groupchatrecord g join user u on g.uid = u.uid where gid = ? order by sendtime desc limit 0,100";
        List<GroupRecord> query = template.query(sql, new BeanPropertyRowMapper<GroupRecord>(GroupRecord.class), gid);
        return query;
    }

    @Override
    public void saveGroupRecord(GroupRecord gr) {
        String sql = "insert into groupchatrecord(uid,gid,content) values(?,?,?)";
        template.update(sql, gr.getUid(), gr.getGid(), gr.getContent());
    }

    @Override
    public void savePrivateRecord(PrivateRecord pr) {
        String sql = "insert into privatechatrecord(uid,tuid,content) values(?,?,?)";
        template.update(sql, pr.getUid(), pr.getTuid(), pr.getContent());
    }

    @Override
    public List<PrivateRecord> getPrivateRecord(int uid, int fuid) {
        String sql = "select p.id,p.uid,u.nickname,p.tuid,p.sendtime,p.content from privatechatrecord p join user u on p.uid = u.uid where (p.uid=? and p.tuid=?) or (p.uid=? and p.tuid=?) order by sendtime desc limit 0,100";
        List<PrivateRecord> query = template.query(sql, new BeanPropertyRowMapper<PrivateRecord>(PrivateRecord.class), uid, fuid, fuid, uid);
        return query;


    }
}
