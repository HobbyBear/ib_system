/**
 * @Title: ListSchedulingAndMap.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月13日
 * @version V1.0
 */
package com.bus.dao.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @ClassName: ListSchedulingAndMap
 * @Description: 存排班集合和下拉列表的实体
 * @author yuqingming
 * @date 2020年6月13日
 * @since JDK 1.8
 */
public class ListSchedulingAndMap implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public ListSchedulingAndMap() {
        super();
    }

    /**
     * list排班实体
     */
    private List<Scheduling> schedulingList;

    /**
     * 车辆下拉列表
     */
    private Map<String, String> busMap;

    /**
     * 线路下拉列表
     */
    private Map<String, String> lineMap;

    /**
     * 站点下拉列表
     */
    private Map<String, Vector<String>> stationLineMap;

    /**
     * 用戶下拉列表
     */
    private Map<String, String> userMap;

    public List<Scheduling> getSchedulingList() {
        return schedulingList;
    }

    public void setSchedulingList(List<Scheduling> schedulingList) {
        this.schedulingList = schedulingList;
    }

    public Map<String, String> getBusMap() {
        return busMap;
    }

    public void setBusMap(Map<String, String> busMap) {
        this.busMap = busMap;
    }

    public Map<String, String> getLineMap() {
        return lineMap;
    }

    public void setLineMap(Map<String, String> lineMap) {
        this.lineMap = lineMap;
    }

    public Map<String, Vector<String>> getStationLineMap() {
        return stationLineMap;
    }

    public void setStationLineMap(Map<String, Vector<String>> stationLineMap) {
        this.stationLineMap = stationLineMap;
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, String> userMap) {
        this.userMap = userMap;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schedulingList == null) ? 0 : schedulingList.hashCode());
        result = prime * result + ((busMap == null) ? 0 : busMap.hashCode());
        result = prime * result + ((lineMap == null) ? 0 : lineMap.hashCode());
        result = prime * result + ((stationLineMap == null) ? 0 : stationLineMap.hashCode());
        result = prime * result + ((userMap == null) ? 0 : userMap.hashCode());
        return result;
    }

}
