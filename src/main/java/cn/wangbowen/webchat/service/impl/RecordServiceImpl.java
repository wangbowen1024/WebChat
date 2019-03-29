package cn.wangbowen.webchat.service.impl;

import cn.wangbowen.webchat.dao.RecordDao;
import cn.wangbowen.webchat.dao.impl.RecordDaoImpl;
import cn.wangbowen.webchat.domain.GroupRecord;
import cn.wangbowen.webchat.domain.PrivateRecord;
import cn.wangbowen.webchat.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    RecordDao recordDao = new RecordDaoImpl();

    @Override
    public List<GroupRecord> getGroupRecord(int gid) {
        return recordDao.getGroupRecord(gid);
    }

    @Override
    public void saveGroupRecord(GroupRecord gr) {
        recordDao.saveGroupRecord(gr);
    }

    @Override
    public void savePrivateRecord(PrivateRecord pr) {
        recordDao.savePrivateRecord(pr);
    }

    @Override
    public List<PrivateRecord> getPrivateRecord(int uid, int fuid) {
        return recordDao.getPrivateRecord(uid, fuid);
    }
}
