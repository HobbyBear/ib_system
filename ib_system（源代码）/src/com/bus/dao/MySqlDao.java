package com.bus.dao;

/**
 * @author Administrator
 * 存放各种mysql语句
 */
public class MySqlDao {
    /**
     * 根据用户名查找密码
     */
    public static String findUserPasswordByName = "select u_password from user where u_loginName = ?";

    /**
     * 查找所有角色
     */
    public static String findAllRole = "select *from role";

    /**
     * 根据登录名查询权限
     */
    public static String findAuthorityListByName = "select p_name from permission_view where u_loginName = ?";

    /**
     * 查询所有用户
     */
    public static String find = "select *from user";

    /**
     * 根据登录名查找用户
     */
    public static String findUserByName = "select *from user where u_loginName = ?";

    /**
     * 修改密码
     */
    public static String changePass = "update user set u_password = ? where u_loginName = ?";

    /**
     * 保存用户信息
     */
    public static String save = "insert into user values(?,?,?,?,?,?,?,?,?)";

    /**
     * 修改用户信息
     */
    public static String update = "update user set u_id = ?, r_id = ?, u_loginName = ?, u_password = ?, u_name = ?, u_telephone = ?,u_idCard = ?, u_driving = ?, u_status = ? where u_id = ?";

    /**
     * 删除用户
     */
    public static String dropUser = "delete from user where u_id = ?";

    /**
     * 条件查询车辆
     */
    public static String findBuses = "select *from bus where b_license = ? and b_status = ?";

    /**
     * 查询所有车辆
     */
    public static String findAllBuses = "select *from bus";

    /**
     * 修改车辆信息
     */
    public static String updateBus = "update bus set b_id = ?,b_license = ?, b_type = ?, b_status = ?,b_startTime = ? where b_id = ?";

    /**
     * 删除车辆
     */
    public static String dropBus = "delete from bus where b_id = ?";

    /**
     * 保存车辆
     */
    public static String saveBus = "insert into bus values(?,?,?,?,?)";

    /**
     * 查询所有线路
     */
    public static String findAllLines = "select *from line";

    /**
     * 修改线路信息
     */
    public static String updateLine = "update line set l_id = ?, l_name = ?, l_status = ?, l_startLineTime = ?, l_direction = ? where l_id = ?";

    /**
     * 修改线路和站点的对应关系
     */
    public static String updateLineStation = "";

    /**
     * 删除线路
     */

    public static String dropLine = "delete from line where l_id = ?";

    /**
     * 保存线路
     */
    public static String saveLine = "insert into line values(?,?,?,?,?)";

    /**
     * 查询所有排班
     */
    public static String findAllScheduling = "select *from scheduling";

    /**
     * 保存排班
     */
    public static String saveScheuling = "insert into scheduling values(?,?,?,?,?,?,?,?)";

    /**
     * 更改排班
     */
    public static String updateScheduling = "update scheduling set sche_id = ?,l_id = ?,b_license = ?,sche_tcNumber = ?,sche_tcTime  = ?,u_id = ?,s_srartStation = ?,s_endStation = ? where sche_id = ?";

    /**
     * 删除排班
     */
    public static String dropScheduling = "delete from scheduling where sche_id = ?";


    /**
     * 查询所有站点
     */
    public static String findAkllStation = "select *from station";

    /**
     * 保存站点
     */
    public static String saveStation = "insert into station values(?,?,?,?,?,?)";

    /**
     * 更改站点
     */
    public static String updateStation = "update station set s_id = ?,s_name = ?,s_longitude = ?,s_latitude=?,s_location = ?,s_street = ? where s_id = ?";

    /**
     * 删除站点
     */
    public static String dropStation = "delete from station where s_id = ?";

    /**
     * 通过状态查询车辆
     **/
    public static String findBusesByStatus = "select *from bus where b_status = ?";

    /**
     * 通过车牌号查找所有车辆
     */
    public static String findAllBusesByLicense = "select *from bus where b_license = ?";

    /**
     * 通过车牌查找车辆
     */
    public static String findBusesByLicense = "select *from bus where b_license = ? and b_status = ?";

    public static String saveBusNoStartTime = "insert into bus(b_license,b_type,b_status) values (?,?,?)";

    /**
     * 根据站点名查询
     */
    public static String findStationByName = "select *from station where s_name = ?";

    /**
     * 根据区域查询
     */
    public static String findStationByRegion = "select *from station where s_location = ?";

    /**
     * 根据街道查询
     */
    public static String findStationByStreet = "select *from station where s_street = ?";

    /**
     * 根据站点名、区域查询
     */
    public static String findStationByNameRegion = "select *from station where s_name = ? and s_location = ?";

    /**
     * 根据站点名、街道查询
     */
    public static String findStationByNameStreet = "select *from station where s_name = ? and s_street = ?";

    /**
     * 根据街道、区域查询
     */
    public static String findStationByStreetRegion = "select *from station where s_street = ? and s_location = ?";

    /**
     * 根据站点名、街道、区域查询
     */
    public static String findStationNameByStreetRegion = "select *from station where s_name = ? and s_street = ? and s_location = ?";

    /**
     * 根据线路名称查询
     */
    public static String findLinesByName = "select *from line where l_name = ?";

    /**
     * 根据方向查询线路
     */
    public static String findLinesDirection = "select *from line where l_direction = ?";

    /**
     * 根据状态查询线路
     */
    public static String findLinesStatus = "select *from line where l_status = ?";

    /**
     * 根据线路名、方向查询
     */
    public static String findLinesByNameDirection = "select *from line where l_name = ? and l_direction = ?";

    /**
     * 根据线路名状态查询
     */
    public static String findLinesByNameStatus = "select *from line where l_name = ? and l_status = ?";

    /**
     * 根据线路名、方向、状态查询
     */
    public static String findLinesByNameDirectionStatus = "select *from line where l_name = ?and l_direction = ? and l_status = ?";

    /**
     * 根据方向、状态查询
     */
    public static String findLinesByDirectionStatus = "select *from line where l_direction = ? and l_status = ?";

    /**
     * 根据线路的编号查询其所有的站点名称
     */
    public static String findStaionByLine = "SELECT station.s_name from station JOIN line_station ON line_station.s_id = station.s_id AND line_station.l_id = ?";

    /**
     * 删除线路站点关系表中的对应关系
     */
    public static String deleteAllLineStationRef = "delete from line_station where l_id = ?";

    /**
     * 添加线路站点关系表中的对应关系
     */
    public static String addLineStationRef = "insert into line_station values(?,?,?)";

    /**
     * 根据站点名称查询编号
     */
    public static String findStaionIdByName = "select s_id from station where s_name = ?";

    /**
     * 【新添加的路线】根据路线名称找到路线的编号
     */
    public static String findLinesIdByName = "select l_id from line where l_name = ?";

    /**
     * 查询所有用户
     */
    public static String findAllUer = "select *from user";

    /**
     * 根据用户名查找
     */
    public static String findUerByName = "select *from user where u_name = ?";

    /**
     * 根据角色查找
     */
    public static String findUserByRole = "select *from user where r_id = ?";

    /**
     * 根据状态找
     */
    public static String findUserByStatus = "select *from user where u_status = ?";

    /**
     * 根据用户名、角色找
     */
    public static String findUserByNameRole = "select *from user where  u_name = ? and  r_id = ?";

    /**
     * 根据用户名、状态找
     */
    public static String findUserByNameStatus = "select *from user where  u_name = ? and  u_status = ?";

    /**
     * 根据角色、状态找
     */
    public static String findUserByRoleStatus = "select *from user where r_id = ? and u_status = ?";

    /**
     * 根据用户名、角色、状态找
     */
    public static String findUserByNameRoleStatus = "select *from user where  u_name = ? and  r_id = ? and u_status = ?";

    /**
     * 根据路线编号查找名称
     */
    public static String findLineNameByCode = "select l_name from line where l_id = ?";

    /**
     * 根据站点编号查找站点名
     */
    public static String findStaionNameByCode = "select s_name from station where s_id = ?";

    /**
     * 根据用户编号查找用户名
     */
    public static String findUserNameByCode = "select u_name from user where u_id = ?";

    /**
     * 根据车牌号查询
     */
    public static String findSchedulingByBusLicense = "select *from scheduling where b_license = ?";

    /**
     * 根据线路名称查询
     */
    public static String findSchedulingByLineName = "select *from scheduling where l_id = ?";

    /**
     * 根据起始站名称查询
     */
    public static String findSchedulingByStartStation = "select *from scheduling where s_srartStation = ?";

    /**
     * 根据终点站名称查询
     */
    public static String findSchedulingByEndStation = "select *from scheduling where s_endStation = ?";

    /**
     * 根据车牌、线路名查询
     */
    public static String findSchedulingByBusLicenseLineName = "select *from scheduling where b_license = ? and  l_id  = ?";

    /**
     * 根据车牌、始发站查询
     */
    public static String findSchedulingByBusLicenseStartStation = "select *from scheduling where b_license = ? and  s_srartStation  = ?";
    /**
     * 根据车牌、终点站查询
     */
    public static String findSchedulingByBusLicenseEndStation = "select *from scheduling where b_license = ? and  s_endStation  = ?";

    /**
     * 根据线路名、始发站查询
     */
    public static String findSchedulingByLineNameStartStation = "select *from scheduling where l_id = ? and  s_srartStation  = ?";
    /**
     * 根据线路名、终点站查询
     */
    public static String findSchedulingByLineNameEndStation = "select *from scheduling where l_id = ? and  s_endStation  = ?";
    /**
     * 根据始发站、终点站查询
     */
    public static String findSchedulingByStartStationEndStation = "select *from scheduling where s_srartStation = ? and  s_endStation  = ?";

    /**
     * 根据车牌、线路名、始发站查询
     */
    public static String findSchedulingByBusLicenseLineNameStartStation = "select *from scheduling where b_license = ? and  l_id  = ? and s_srartStation = ?";
    /**
     * 根据车牌、线路名、终点站查询
     */
    public static String findSchedulingByBusLicenseLineNameEndStation = "select *from scheduling where b_license = ? and  l_id  = ? and s_endStation = ?";
    /**
     * 根据车牌、始发站、终点站查询
     */
    public static String findSchedulingByBusLicenseStartStationEndStation = "select *from scheduling where b_license = ? and s_srartStation = ? and s_endStation = ?";
    /**
     * 根据线路名、始发站、终点站查询
     */
    public static String findSchedulingByLineNameStartStationEndStation = "select *from scheduling where l_id = ? and s_srartStation = ? and s_endStation = ?";

    /**
     * 根据车牌、线路名、始发站、终点站查询
     */
    public static String findSchedulingByBusLicenseLineNameStartStationEndStation = "select *from scheduling where l_id = ? and s_srartStation = ? and s_endStation = ?";
    ;
    /**
     * 根据线路名查询线路编号
     */
    public static String findLinesCodeByName = "select l_id from line where l_name = ?";

    /**
     * 根据用户名查询用户编号
     */
    public static String findUserCodeByName = "select u_id from user where u_name = ?";
    /**
     * 根据站点名查询站点编号
     */
    public static String findStationCodeByName = "select s_id from station where s_name = ?";
}
