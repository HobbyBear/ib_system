package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.DaoFactory;
import com.bus.dao.LineDao;
import com.bus.dao.model.Line;
import com.bus.service.LineService;

public class LineServiceImpl implements LineService {

    @Override
    public String save(Map<String, List<Line>> dataMapList) {
        try {
            LineDao dao = DaoFactory.getLineDao();
            List<Line> deleteList = dataMapList.get("DELETE");
            if (deleteList != null && deleteList.size() > 0) {
                for (Line line : deleteList) {
                    int mess = dao.dropLine(line);
                    // 如果删除线路失败
                    if (mess == 0) {
                        return "操作失败";
                    }
                }
            }

            List<Line> updateList = dataMapList.get("UPDATE");
            if (updateList != null && updateList.size() > 0) {
                for (Line line : updateList) {
                    dao.updateLine(line);
                }
            }

            List<Line> saveList = dataMapList.get("SAVE");
            if (saveList != null && saveList.size() > 0) {
                for (Line line : saveList) {
                    dao.saveLine(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "操作失败";
        }
        return "操作成功";
    }

    @Override
    public List<Line> find(Line condition) throws SQLException {

        LineDao dao = DaoFactory.getLineDao();
        return dao.find(condition);
    }

    @Override
    public String findNameByCode(String code) throws SQLException {
        LineDao dao = DaoFactory.getLineDao();
        return dao.findNameByCode(code);
    }

    @Override
    public String findCodeByName(String lineName) throws SQLException {
        LineDao dao = DaoFactory.getLineDao();
        return dao.findCodeByName(lineName);
    }
}
