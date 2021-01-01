package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.MySqlDao;
import com.bus.dao.SchedulingDao;
import com.bus.dao.model.Scheduling;
import com.bus.service.LineService;
import com.bus.service.impl.ServiceFactory;
import com.bus.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class SchedulingDaoImpl implements SchedulingDao {

    @Override
    public List<Scheduling> find(Scheduling condition) throws SQLException {
        List<Scheduling> schedulings = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> lists;
        if (!"".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicense, new ArrayListHandler(), condition.getBusCode());
        }

        /* 根据线路名称查询 */
        else if ("".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByLineName, new ArrayListHandler(), condition.getLineCode());
        }

        /* 根据起始站名称查询 */
        else if ("".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByStartStation, new ArrayListHandler(), condition.getStartStation());
        }

        /* 根据终点站名称查询 */
        else if ("".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByEndStation, new ArrayListHandler(), condition.getEndStation());
        }

        // 两个查询条件
        /* 根据车牌号、线路名查询 */
        else if (!"".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseLineName, new ArrayListHandler(), condition.getBusCode(), condition.getLineCode());
        }

        /* 根据车牌号、起始站名查询 */
        else if (!"".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseStartStation, new ArrayListHandler(), condition.getBusCode(), condition.getStartStation());
        }

        /* 根据车牌号、终点站名询 */
        else if (!"".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseEndStation, new ArrayListHandler(), condition.getBusCode(), condition.getEndStation());

        }

        /* 根据线路名、起始站名查询 */
        else if ("".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByLineNameStartStation, new ArrayListHandler(), condition.getLineCode(), condition.getStartStation());

        }

        /* 根据线路名、终点站名查询 */
        else if ("".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByLineNameEndStation, new ArrayListHandler(), condition.getLineCode(), condition.getEndStation());
        }

        /* 根据起始站名、终点站名查询 */
        else if ("".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByStartStationEndStation, new ArrayListHandler(), condition.getStartStation(), condition.getEndStation());
        }

        // 三个查询条件
        /* 根据车牌号、线路名、起始站名查询 */
        else if (!"".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && "".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseLineNameStartStation, new ArrayListHandler(), condition.getBusCode(), condition.getLineCode(), condition.getStartStation());
        }

        /* 根据车牌号、线路名、终点站名查询 */
        else if (!"".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && "".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseLineNameEndStation, new ArrayListHandler(), condition.getBusCode(), condition.getLineCode(), condition.getEndStation());
        }

        /* 根据车牌号、起始站名、终点站名查询 */
        else if (!"".equals(condition.getBusCode()) && "".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseStartStationEndStation, new ArrayListHandler(), condition.getBusCode(), condition.getStartStation(), condition.getEndStation());
        }

        /* 线路名、起始站名、终点站名查询 */
        else if ("".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByLineNameStartStationEndStation, new ArrayListHandler(), condition.getBusCode(), condition.getStartStation(), condition.getEndStation());
        }

        // 四个查询条件
        /* 根据车牌号、线路名、起始站名、终点站名查询 */
        else if (!"".equals(condition.getBusCode()) && !"".equals(condition.getLineCode()) && !"".equals(condition.getStartStation()) && !"".equals(condition.getEndStation())) {
            lists = queryRunner.query(MySqlDao.findSchedulingByBusLicenseLineNameStartStationEndStation, new ArrayListHandler(), condition.getBusCode(), condition.getLineCode(), condition.getStartStation(), condition.getEndStation());
        }
        // 没有输入条件，随便全部找
        else {
            lists = queryRunner.query(MySqlDao.findAllScheduling, new ArrayListHandler());
        }

        for (Object[] object : lists) {
            Scheduling scheduling = new Scheduling();
            scheduling.setCode(String.valueOf(object[0]));
            scheduling.setLineCode(String.valueOf(object[1]));
            scheduling.setBusCode(String.valueOf(object[2]));
            scheduling.setTcNumber(String.valueOf(object[3]));
            scheduling.setTcTime(String.valueOf(object[4]));
            scheduling.setUserCode(String.valueOf(object[5]));
            scheduling.setStartStation(String.valueOf(object[6]));
            scheduling.setEndStation(String.valueOf(object[7]));
            schedulings.add(scheduling);
        }
        return schedulings;
    }

    @Override
    public void updateScheduling(Scheduling scheduling) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.updateScheduling, scheduling.getCode(), scheduling.getLineCode(), scheduling.getBusCode(), scheduling.getTcNumber(), scheduling.getTcTime(), scheduling.getUserCode(), scheduling.getStartStation(), scheduling.getEndStation(), scheduling.getCode());
        System.out.println("【排班更新】更新了 " + row + " 条记录");
    }

    @Override
    public void dropScheduling(Scheduling scheduling) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.dropScheduling, scheduling.getCode());
        System.out.println("【排班删除】删除了 " + row + " 条记录");
    }

    @Override
    public void saveScheduling(Scheduling scheduling) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.saveScheuling, scheduling.getCode(), scheduling.getLineCode(), scheduling.getBusCode(), scheduling.getTcNumber(), scheduling.getTcTime(), scheduling.getUserCode(), scheduling.getStartStation(), scheduling.getEndStation());
        System.out.println("【排班保存】保存了 " + row + " 条记录");
    }

}