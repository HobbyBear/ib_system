package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.MySqlDao;
import com.bus.dao.StationDao;
import com.bus.dao.model.Station;
import com.bus.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.rowset.internal.Row;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class StationDaoImpl implements StationDao {

    @Override
    public List<Station> find(Station condition) throws SQLException {
        List<Station> stations = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> lists;
        // 没有输入查询条件，查询全部
        if ("".equals(condition.getStationName()) && "".equals(condition.getRegion()) && "".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findAkllStation, new ArrayListHandler());
        }
        // 根据站点名查询
        else if (!"".equals(condition.getStationName()) && "".equals(condition.getRegion()) && "".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByName, new ArrayListHandler(), condition.getStationName());
        }
        // 根据区域查询
        else if ("".equals(condition.getStationName()) && !"".equals(condition.getRegion()) && "".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByRegion, new ArrayListHandler(), condition.getRegion());
        }
        // 根据街道查询
        else if ("".equals(condition.getStationName()) && "".equals(condition.getRegion()) && !"".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByStreet, new ArrayListHandler(), condition.getStreet());
        }
        // 根据站点名、区域查询
        else if (!"".equals(condition.getStationName()) && !"".equals(condition.getRegion()) && "".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByNameRegion, new ArrayListHandler(), condition.getStationName(), condition.getRegion());
        }
        // 根据站点名、街道查询
        else if (!"".equals(condition.getStationName()) && "".equals(condition.getRegion()) && !"".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByNameStreet, new ArrayListHandler(), condition.getStationName(), condition.getStreet());
        }
        // 根据街道、区域查询
        else if ("".equals(condition.getStationName()) && !"".equals(condition.getRegion()) && !"".equals(condition.getStreet())) {
            lists = queryRunner.query(MySqlDao.findStationByStreetRegion, new ArrayListHandler(), condition.getStreet(), condition.getRegion());
        }
        // 根据站点名、区域、街道查询
        else {
            lists = queryRunner.query(MySqlDao.findStationNameByStreetRegion, new ArrayListHandler(), condition.getStationName(), condition.getStreet(), condition.getRegion());
        }
        for (Object[] objects : lists) {
            Station station = new Station();
            station.setStationCode(String.valueOf(objects[0]));
            station.setStationName(String.valueOf(objects[1]));
            station.setLongitude(String.valueOf(objects[2]));
            station.setLatitude(String.valueOf(objects[3]));
            station.setRegion(String.valueOf(objects[4]));
            station.setStreet(String.valueOf(objects[5]));
            stations.add(station);
        }
        return stations;
    }

    @Override
    public void updateStation(Station station) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.updateStation, station.getStationCode(), station.getStationName(), station.getLongitude(), station.getLatitude(), station.getRegion(), station.getStreet(), station.getStationCode());
        System.out.println("【修改站点】修改了 " + row + "行");
    }

    @Override
    public void dropStation(Station station) throws SQLException {
        System.out.println(station);
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.dropStation, station.getStationCode());
        System.out.println("【删除站点】删除了 " + row + "行");
    }

    @Override
    public void saveStation(Station station) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.saveStation, null, station.getStationName(), station.getLongitude(), station.getLatitude(), station.getRegion(), station.getStreet());
        System.out.println("【添加站点】添加了 " + row + "行");
    }

    @Override
    public String findNameByCode(String stationCode) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Object name = queryRunner.query(MySqlDao.findStaionNameByCode, new ScalarHandler<>(), stationCode);
        if (name != null) {
            return String.valueOf(name);
        }
        return "";
    }

    @Override
    public String findCodeByName(String name) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Object code = queryRunner.query(MySqlDao.findStationCodeByName, new ScalarHandler<>(), name);
        if (code != null) {
            return String.valueOf(code);
        }
        return "";
    }
}
