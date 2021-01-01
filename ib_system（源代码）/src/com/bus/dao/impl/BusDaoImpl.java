package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.BusDao;
import com.bus.dao.MySqlDao;
import com.bus.dao.model.Bus;
import com.bus.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class BusDaoImpl implements BusDao {

    @Override
    public List<Bus> find(Bus condition) throws SQLException {
        List<Bus> buses = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> lists;
        // 如果不输入车牌号查询
        if ("".equals(condition.getBusLicense())) {
            // 状态为 "启用"
            if ("1".equals(condition.getBusStatus())) {
                lists = queryRunner.query(MySqlDao.findBusesByStatus, new ArrayListHandler(), "启用");
            }
            // 状态为 “全部”
            else if ("2".equals(condition.getBusStatus())) {
                lists = queryRunner.query(MySqlDao.findAllBuses, new ArrayListHandler());
            }
            // 状态为 “停运”
            else {
                lists = queryRunner.query(MySqlDao.findBusesByStatus, new ArrayListHandler(), "停运");
            }
        }
        // 有车牌号查询
        else {
            // 状态为 “全部”
            if ("2".equals(condition.getBusStatus())) {
                lists = queryRunner.query(MySqlDao.findAllBusesByLicense, new ArrayListHandler(), condition.getBusLicense());
            }
            // 状态为 "启用"
            else if ("1".equals(condition.getBusStatus())) {
                lists = queryRunner.query(MySqlDao.findBusesByLicense, new ArrayListHandler(), condition.getBusLicense(), "启用");
            }
            // 状态为 “停运”
            else {
                lists = queryRunner.query(MySqlDao.findBusesByLicense, new ArrayListHandler(), condition.getBusLicense(), "停运");
            }
        }
        for (Object[] objects : lists) {
            Bus bus = new Bus();
            bus.setBusCode(String.valueOf(objects[0]));
            bus.setBusLicense(String.valueOf(objects[1]));
            bus.setBusType(String.valueOf(objects[2]));
            bus.setBusStatus(String.valueOf(objects[3]));
            bus.setStartTime(String.valueOf(objects[4]));
            buses.add(bus);
        }
        return buses;
    }

    @Override
    public void updateBus(Bus bus) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        if ("1".equals(bus.getBusStatus())) {
            bus.setBusStatus("启用");
        } else {
            bus.setBusStatus("停运");
        }
        int row = queryRunner.update(MySqlDao.updateBus, bus.getBusCode(), bus.getBusLicense(), bus.getBusType(), bus.getBusStatus(), bus.getStartTime(), bus.getBusCode());
        System.out.println("【修改车辆信息】修改了 " + row + " 行");
    }

    @Override
    public void dropBus(Bus bus) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.dropBus, bus.getBusCode());
        System.out.println("【删除车辆】删除了 " + row + " 行");
    }

    @Override
    public void saveBus(Bus bus) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row;
        // 如果没有启运时间
        if ("".equals(bus.getStartTime())) {
            row = queryRunner.update(MySqlDao.saveBusNoStartTime, bus.getBusLicense(), bus.getBusType(), bus.getBusStatus());
        } else {
            row = queryRunner.update(MySqlDao.saveBus, null, bus.getBusLicense(), bus.getBusType(), bus.getBusStatus(), bus.getStartTime());
        }
        System.out.println("【添加车辆信息】添加了 " + row + " 行");
    }

}