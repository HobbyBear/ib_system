package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.DaoFactory;
import com.bus.dao.SchedulingDao;
import com.bus.dao.StationDao;
import com.bus.dao.impl.SchedulingDaoImpl;
import com.bus.dao.model.Scheduling;
import com.bus.dao.model.Station;
import com.bus.service.SchedulingService;

public class SchedulingServiceImpl implements SchedulingService {

    @Override
    public String save(Map<String, List<Scheduling>> dataMapList) {
        try {
            SchedulingDao dao = DaoFactory.getSchedulingDao();
            List<Scheduling> deleteList = dataMapList.get("DELETE");
            if (deleteList != null && deleteList.size() > 0) {
                for (Scheduling scheduling : deleteList) {
                    dao.dropScheduling(scheduling);
                }
            }

            List<Scheduling> updateList = dataMapList.get("UPDATE");
            if (updateList != null && updateList.size() > 0) {
                for (Scheduling scheduling : updateList) {
                    dao.updateScheduling(scheduling);
                }
            }

            List<Scheduling> saveList = dataMapList.get("SAVE");
            if (saveList != null && saveList.size() > 0) {
                for (Scheduling scheduling : saveList) {
                    dao.saveScheduling(scheduling);
                }
            }
        } catch (Exception e) {
            return "操作失败";
        }
        return "操作成功";
    }

    @Override
    public List<Scheduling> find(Scheduling condition) throws SQLException {
        SchedulingDao schedulingDao = new SchedulingDaoImpl();
        return schedulingDao.find(condition);
    }
}
