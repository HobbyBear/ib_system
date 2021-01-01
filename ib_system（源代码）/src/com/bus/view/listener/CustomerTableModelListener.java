package com.bus.view.listener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.bus.dao.model.*;
import com.bus.enums.ModuleEnum;
import com.bus.service.LineService;
import com.bus.service.StationService;
import com.bus.service.UserService;
import com.bus.service.impl.ServiceFactory;

public class CustomerTableModelListener implements TableModelListener {

    private ModuleEnum currentModel;

    private Map<String, List<Object>> changeMapList;

    private JTable table;

    public CustomerTableModelListener(ModuleEnum currentModel, JTable table) {
        super();
        this.currentModel = currentModel;
        this.table = table;
        changeMapList = new HashMap<String, List<Object>>();
        changeMapList.put("UPDATE", new ArrayList<>());
        changeMapList.put("DELETE", new ArrayList<>());
        // 自己加的
        changeMapList.put("SAVE", new ArrayList<>());
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = 0;
        row = e.getFirstRow();
        if (row >= 0) {
            if (TableModelEvent.UPDATE == e.getType()) {
                switch (currentModel) {
                    case BUS:
                        if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
                            String busCode = String.valueOf(table.getValueAt(row, 0));
                            List<Object> updateList = changeMapList.get("UPDATE");
                            Bus bus = new Bus();
                            bus.setBusCode(String.valueOf(table.getValueAt(row, 0)));
                            bus.setBusLicense(String.valueOf(table.getValueAt(row, 1)));
                            bus.setBusType(String.valueOf(table.getValueAt(row, 2)));
                            if (String.valueOf(table.getValueAt(row, 3)) == null) {
                                bus.setBusStatus(null);
                            } else {
                                String busStatus = String.valueOf(table.getValueAt(row, 3));
                                if ("启用".equals(busStatus)) {
                                    bus.setBusStatus("1");
                                } else {
                                    bus.setBusStatus("0");
                                }
                            }
                            bus.setStartTime(String.valueOf(table.getValueAt(row, 4)));

                            boolean isSet = false;
                            for (int i = 0; i < updateList.size(); i++) {
                                Bus updateBus = (Bus) updateList.get(i);
                                if (updateBus.getBusCode().equals(busCode)) {
                                    updateList.set(i, bus);
                                    isSet = true;
                                    break;
                                }
                            }
                            if (!isSet) {
                                updateList.add(bus);
                            }
                        }
                        break;
                    case USER:
                        if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
                            String userCode = String.valueOf(table.getValueAt(row, 0));
                            List<Object> updateList = changeMapList.get("UPDATE");
                            User user = new User();
                            user.setCode(String.valueOf(table.getValueAt(row, 0)));
                            user.setLoginName(String.valueOf(table.getValueAt(row, 1)));
                            user.setName(String.valueOf(table.getValueAt(row, 2)));
                            user.setPassword(String.valueOf(table.getValueAt(row, 8)));
                            user.setPhone(String.valueOf(table.getValueAt(row, 3)));
                            user.setIdCard(String.valueOf(table.getValueAt(row, 4)));
                            if (table.getValueAt(row, 5) == null || String.valueOf(table.getValueAt(row, 5)).length() == 0) {
                                user.setRole(null);
                            } else {
                                String roleText = String.valueOf(table.getValueAt(row, 5));
                                if ("驾驶员".equals(roleText)) {
                                    user.setRole("1");
                                } else if ("管理员".equals(roleText)) {
                                    user.setRole("2");
                                } else {
                                    user.setRole("3");
                                }
                            }
                            user.setDriving(Integer.parseInt(String.valueOf(table.getValueAt(row, 6))));
                            user.setStatus(String.valueOf(table.getValueAt(row, 7)));
                            boolean isSet = false;
                            for (int i = 0; i < updateList.size(); i++) {
                                User updateUser = (User) updateList.get(i);
                                if (updateUser.getCode().equals(userCode)) {
                                    updateList.set(i, user);
                                    isSet = true;
                                    break;
                                }
                            }
                            if (!isSet) {
                                updateList.add(user);
                            }

                        }
                        break;
                    case LINE:
                        if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
                            String LineCode = String.valueOf(table.getValueAt(row, 0));
                            List<Object> updateList = changeMapList.get("UPDATE");
                            Line line = new Line();
                            line.setLineCode(String.valueOf(table.getValueAt(row, 0)));
                            line.setLineName(String.valueOf(table.getValueAt(row, 1)));
                            if (String.valueOf(table.getValueAt(row, 2)) == null) {
                                line.setStatus(null);
                            } else {
                                String busStatus = String.valueOf(table.getValueAt(row, 2));
                                if ("启用".equals(busStatus)) {
                                    line.setStatus("1");
                                } else {
                                    line.setStatus("0");
                                }
                            }
                            line.setStartLineTime(String.valueOf(table.getValueAt(row, 3)));
                            line.setDirection(String.valueOf(table.getValueAt(row, 4)));
                            if ("上行".equals(line.getDirection())) {
                                line.setDirection("1");
                            } else {
                                line.setDirection("0");
                            }
                            String stationCodes = String.valueOf(String.valueOf(table.getValueAt(row, 5)));
                            line.setAllStation(stationCodes);

                            boolean isSet = false;
                            for (int i = 0; i < updateList.size(); i++) {
                                Line updateLine = (Line) updateList.get(i);
                                if (updateLine.getLineCode().equals(LineCode)) {
                                    updateList.set(i, line);
                                    isSet = true;
                                    break;
                                }
                            }
                            if (!isSet) {
                                updateList.add(line);
                            }
                        }

                        break;
                    case STATION:
                        if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
                            String StationCode = String.valueOf(table.getValueAt(row, 0));
                            List<Object> updateList = changeMapList.get("UPDATE");
                            Station station = new Station();
                            station.setStationCode(String.valueOf(table.getValueAt(row, 0)));
                            station.setStationName(String.valueOf(table.getValueAt(row, 1)));
                            station.setLongitude(String.valueOf(table.getValueAt(row, 2)));
                            station.setLatitude(String.valueOf(table.getValueAt(row, 3)));
                            station.setRegion(String.valueOf(table.getValueAt(row, 4)));
                            station.setStreet(String.valueOf(table.getValueAt(row, 5)));
                            boolean isSet = false;
                            for (int i = 0; i < updateList.size(); i++) {
                                Station updateStation = (Station) updateList.get(i);
                                if (updateStation.getStationCode().equals(StationCode)) {
                                    updateList.set(i, station);
                                    isSet = true;
                                    break;
                                }
                            }
                            if (!isSet) {
                                updateList.add(station);
                            }
                        }
                        break;
                    case SCHEDULING:
                        if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
                            String schedulingCode = String.valueOf(table.getValueAt(row, 0));
                            List<Object> updateList = changeMapList.get("UPDATE");
                            Scheduling schedulingData = new Scheduling();
                            schedulingData.setCode(String.valueOf(table.getValueAt(row, 0)));
                            // 找到线路编号
                            LineService lineService = ServiceFactory.getLineService();
                            String lineCode = null;
                            try {
                                lineCode = lineService.findCodeByName(String.valueOf(table.getValueAt(row, 1)));
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            schedulingData.setLineCode(lineCode);

                            schedulingData.setBusCode(String.valueOf(table.getValueAt(row, 2)));
                            schedulingData.setTcNumber(String.valueOf(table.getValueAt(row, 3)));
                            schedulingData.setTcTime(String.valueOf(table.getValueAt(row, 4)));

                            // 找到司机编号
                            UserService userService = ServiceFactory.getUserService();
                            String userCode = null;
                            try {
                                userCode = userService.findCodeByName(String.valueOf(table.getValueAt(row, 5)));
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            schedulingData.setUserCode(userCode);

                            // 找到站点编号
                            StationService stationService = ServiceFactory.getStationService();
                            try {
                                String code = stationService.findCodeByName(String.valueOf(table.getValueAt(row, 6)));
                                schedulingData.setStartStation(code);
                                code = stationService.findCodeByName(String.valueOf(table.getValueAt(row, 7)));
                                schedulingData.setEndStation(code);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            boolean isSet = false;
                            for (int i = 0; i < updateList.size(); i++) {
                                Scheduling updateScheduling = (Scheduling) updateList.get(i);
                                if (updateScheduling.getCode().equals(schedulingCode)) {
                                    updateList.set(i, schedulingData);
                                    isSet = true;
                                    break;
                                }
                            }
                            if (!isSet) {
                                updateList.add(schedulingData);
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }

    public void resetMap() {
        changeMapList.get("DELETE").clear();
        changeMapList.get("UPDATE").clear();
    }

    public Map<String, List<Object>> getChangeMapList() {
        return changeMapList;
    }
}
