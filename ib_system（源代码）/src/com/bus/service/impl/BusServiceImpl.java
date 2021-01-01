package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.BusDao;
import com.bus.dao.DaoFactory;
import com.bus.dao.model.Bus;
import com.bus.service.BusService;

public class BusServiceImpl implements BusService {

    @Override
    public String save(Map<String, List<Bus>> dataMapList) {
        try {
            BusDao dao = DaoFactory.getBusDao();
            List<Bus> deleteList = dataMapList.get("DELETE");
            if (deleteList != null && deleteList.size() > 0) {
                for (Bus bus : deleteList) {
                    dao.dropBus(bus);
                }
            }

            List<Bus> updateList = dataMapList.get("UPDATE");
            if (updateList != null && updateList.size() > 0) {
                for (Bus bus : updateList) {
                    dao.updateBus(bus);
                }
            }

            List<Bus> saveList = dataMapList.get("SAVE");
            if (saveList != null && saveList.size() > 0) {
                for (Bus bus : saveList) {
                    dao.saveBus(bus);
                }
            }

        } catch (Exception e) {
            return "车辆状态不能为空！";
        }
        return "操作成功";
    }

    @Override
    public List<Bus> find(Bus condition) throws SQLException {
        BusDao dao = DaoFactory.getBusDao();
        return dao.find(condition);
    }
}
