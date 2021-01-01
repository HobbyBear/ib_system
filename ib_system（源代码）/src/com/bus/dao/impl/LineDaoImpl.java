package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.LineDao;
import com.bus.dao.MySqlDao;
import com.bus.dao.model.Line;
import com.bus.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class LineDaoImpl implements LineDao {

    @Override
    public List<Line> find(Line condition) throws SQLException {
        List<Line> lines = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> lists;
        // 查询全部的线路
        if ("".equals(condition.getLineName()) && "2".equals(condition.getDirection()) && "2".equals(condition.getStatus())) {
            lists = queryRunner.query(MySqlDao.findAllLines, new ArrayListHandler());
        }
        // 根据线路名查询
        else if (!"".equals(condition.getLineName()) && "2".equals(condition.getDirection()) && "2".equals(condition.getStatus())) {
            lists = queryRunner.query(MySqlDao.findLinesByName, new ArrayListHandler(), condition.getLineName());
        }
        // 根据方向查询
        else if ("".equals(condition.getLineName()) && "2".equals(condition.getStatus()) && !"2".equals(condition.getDirection())) {
            // 上行
            if ("1".equals(condition.getDirection())) {
                lists = queryRunner.query(MySqlDao.findLinesDirection, new ArrayListHandler(), "上行");
            }
            // 下行
            else {
                lists = queryRunner.query(MySqlDao.findLinesDirection, new ArrayListHandler(), "下行");
            }
        }
        // 根据状态查询
        else if ("".equals(condition.getLineName()) && "2".equals(condition.getDirection()) && !"2".equals(condition.getStatus())) {
            // 启用
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findLinesStatus, new ArrayListHandler(), "启用");
            }
            // 停运
            else {
                lists = queryRunner.query(MySqlDao.findLinesStatus, new ArrayListHandler(), "停运");
            }
        }
        // 根据线路名、方向查询
        else if (!"".equals(condition.getLineName()) && !"2".equals(condition.getDirection()) && "2".equals(condition.getStatus())) {
            if ("1".equals(condition.getDirection())) {
                lists = queryRunner.query(MySqlDao.findLinesByNameDirection, new ArrayListHandler(), condition.getLineName(), "上行");
            } else {
                lists = queryRunner.query(MySqlDao.findLinesByNameDirection, new ArrayListHandler(), condition.getLineName(), "下行");
            }
        }
        // 根据线路名、状态查询
        else if (!"".equals(condition.getLineName()) && "2".equals(condition.getDirection()) && !"2".equals(condition.getStatus())) {
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findLinesByNameStatus, new ArrayListHandler(), condition.getLineName(), "启用");
            } else {
                lists = queryRunner.query(MySqlDao.findLinesByNameStatus, new ArrayListHandler(), condition.getLineName(), "停运");
            }
        }
        // 根据方向、状态查询
        else if ("".equals(condition.getLineName()) && !"2".equals(condition.getDirection()) && !"2".equals(condition.getStatus())) {
            if ("1".equals(condition.getStatus())) {
                if ("1".equals(condition.getDirection())) {
                    lists = queryRunner.query(MySqlDao.findLinesByDirectionStatus, new ArrayListHandler(), "上行", "启用");
                } else {
                    lists = queryRunner.query(MySqlDao.findLinesByDirectionStatus, new ArrayListHandler(), "下行", "启用");
                }
            } else {
                if ("1".equals(condition.getDirection())) {
                    lists = queryRunner.query(MySqlDao.findLinesByDirectionStatus, new ArrayListHandler(), "上行", "停运");
                } else {
                    lists = queryRunner.query(MySqlDao.findLinesByDirectionStatus, new ArrayListHandler(), "下行", "停运");
                }
            }
        } else {
            // 根据线路名、方向、状态查询
            if ("1".equals(condition.getStatus())) {
                if ("1".equals(condition.getDirection())) {
                    lists = queryRunner.query(MySqlDao.findLinesByNameDirectionStatus, new ArrayListHandler(), condition.getLineName(), "上行", "启用");
                } else {
                    lists = queryRunner.query(MySqlDao.findLinesByNameDirectionStatus, new ArrayListHandler(), condition.getLineName(), "下行", "启用");
                }
            } else {
                if ("1".equals(condition.getDirection())) {
                    lists = queryRunner.query(MySqlDao.findLinesByNameDirectionStatus, new ArrayListHandler(), condition.getLineName(), "上行", "停运");
                } else {
                    lists = queryRunner.query(MySqlDao.findLinesByNameDirectionStatus, new ArrayListHandler(), condition.getLineName(), "下行", "停运");
                }
            }
        }
        for (Object[] objects : lists) {
            Line line = new Line();
            line.setLineCode(String.valueOf(objects[0]));
            line.setLineName(String.valueOf(objects[1]));
            line.setStatus(String.valueOf(objects[2]));
            line.setStartLineTime(String.valueOf(objects[3]));
            line.setDirection(String.valueOf(objects[4]));
            // 根据线路编号在  线路站点关系 表中找到线路的所有站点
            List<Object[]> staions = queryRunner.query(MySqlDao.findStaionByLine, new ArrayListHandler(), line.getLineCode());
            String staionsNames = "";
            for (Object[] object : staions) {
                staionsNames += String.valueOf(object[0]) + ",";
            }
            line.setAllStation(staionsNames);
            lines.add(line);
        }
        return lines;
    }

    @Override
    public void updateLine(Line line) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        if ("1".equals(line.getStatus())) {
            line.setStatus("启用");
        } else {
            line.setStatus("停运");
        }
        if ("1".equals(line.getDirection())) {
            line.setDirection("上行");
        } else {
            line.setDirection("下行");
        }
        // 更新线路表
        int row = queryRunner.update(MySqlDao.updateLine, line.getLineCode(), line.getLineName(), line.getStatus(), line.getStartLineTime(), line.getDirection(), line.getLineCode());
        // 更新 线路站点关系表
        // 删除 表中原有的对应关系
        int rowStation1 = queryRunner.update(MySqlDao.deleteAllLineStationRef, line.getLineCode());
        String[] strs = line.getAllStation().split(",");
        for (String string : strs) {
            // 利用站点名称找到站点编号
            String s_id = "" + queryRunner.query(MySqlDao.findStaionIdByName, new ScalarHandler<>(), string);
            // 将关系添加到 线路站点关系表 中
            queryRunner.update(MySqlDao.addLineStationRef, null, line.getLineCode(), s_id);
        }
        System.out.println("【修改线路】修改了 " + row + " 行");
        if (rowStation1 < strs.length) {
            System.out.println("    |-增加 " + (strs.length - rowStation1) + " 个站点");
        } else if (rowStation1 > strs.length) {
            System.out.println("    |-减少 " + (rowStation1 - strs.length) + " 个站点");
        }
    }

    @Override
    public int dropLine(Line line) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        // 删除 表中原有的对应关系
        queryRunner.update(MySqlDao.deleteAllLineStationRef, line.getLineCode());
        int row = queryRunner.update(MySqlDao.dropLine, line.getLineCode());
        if (row != 0) {
            System.out.println("【删除线路】删除了 " + row + " 行");
            return row;
        }
        return 0;
    }

    @Override
    public void saveLine(Line line) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        if ("1".equals(line.getStatus())) {
            line.setStatus("启用");
        } else {
            line.setStatus("停运");
        }
        if ("1".equals(line.getDirection())) {
            line.setDirection("上行");
        } else {
            line.setDirection("下行");
        }
        int row = queryRunner.update(MySqlDao.saveLine, null, line.getLineName(), line.getStatus(), line.getStartLineTime(), line.getDirection());
        // 得到增加的线路的编号
        String l_id = "" + queryRunner.query(MySqlDao.findLinesIdByName, new ScalarHandler<>(), line.getLineName());
        // 更新 线路站点关系表
        // 删除 表中原有的对应关系
        queryRunner.update(MySqlDao.deleteAllLineStationRef, line.getLineCode());
        String[] strs = line.getAllStation().split(",");
        for (String string : strs) {
            // 利用站点名称找到站点编号
            String s_id = "" + queryRunner.query(MySqlDao.findStaionIdByName, new ScalarHandler<>(), string);
            // 将关系添加到 线路站点关系表 中
            queryRunner.update(MySqlDao.addLineStationRef, null, l_id, s_id);
        }
        System.out.println("【添加线路】添加了 " + row + " 行");
    }

    @Override
    public String findNameByCode(String lineCode) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String lineName = queryRunner.query(MySqlDao.findLineNameByCode, new ScalarHandler<>(), lineCode);
        return lineName;
    }

    @Override
    public String findCodeByName(String lineName) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Object code = queryRunner.query(MySqlDao.findLinesCodeByName, new ScalarHandler<>(), lineName);
        if (code != null) {
            return String.valueOf(code);
        }
        return "";
    }

}
