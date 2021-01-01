package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.DaoFactory;
import com.bus.dao.StationDao;
import com.bus.dao.model.Station;
import com.bus.service.StationService;

public class StationServiceImpl implements StationService {

    @Override
    public String save(Map<String, List<Station>> dataMapList) {
        try {
            StationDao dao = DaoFactory.getStationDao();
            List<Station> deleteList = dataMapList.get("DELETE");
            if (deleteList != null && deleteList.size() > 0) {
                for (Station station : deleteList) {
                    dao.dropStation(station);
                }
            }

            List<Station> updateList = dataMapList.get("UPDATE");
            if (updateList != null && updateList.size() > 0) {
                for (Station station : updateList) {
                    dao.updateStation(station);
                }
            }

            List<Station> saveList = dataMapList.get("SAVE");
            if (saveList != null && saveList.size() > 0) {
                for (Station station : saveList) {
                    dao.saveStation(station);
                }
            }

        } catch (Exception e) {
            return "操作失败";
        }
        return "操作成功";
    }

    @Override
    public List<Station> find(Station condition) throws SQLException {
        StationDao dao = DaoFactory.getStationDao();
        return dao.find(condition);
    }

    @Override
    public String findNameByCode(String stationCode) throws SQLException {
        StationDao dao = DaoFactory.getStationDao();
        return dao.findNameByCode(stationCode);
    }

    @Override
    public String findCodeByName(String name) throws SQLException {
        StationDao dao = DaoFactory.getStationDao();
        return dao.findCodeByName(name);
    }
}
