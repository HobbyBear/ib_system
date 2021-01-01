/**
 * @Title: Line.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0
 */
package com.bus.dao.model;

import java.io.Serializable;

/**
 * @ClassName: Line
 * @Description: 线路实体
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class Line implements Serializable, Comparable<Line> {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public Line() {
        super();
    }

    /**
     * 线路编号
     */
    private String lineCode;

    /**
     * 线路名称
     */
    private String lineName;

    /**
     * 状态
     */
    private String status;

    /**
     * 开线时间
     */
    private String startLineTime;

    /**
     * 方向
     */
    private String direction;

    /**
     * 包含站点
     */
    private String allStation;

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartLineTime() {
        return startLineTime;
    }

    public void setStartLineTime(String startLineTime) {
        this.startLineTime = startLineTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAllStation() {
        return allStation;
    }

    public void setAllStation(String allStation) {
        this.allStation = allStation;
    }

    @Override
    public String toString() {
        return "Line [lineCode=" + lineCode + ", lineName=" + lineName + ", status=" + status + ", startLineTime="
                + startLineTime + ", direction=" + direction + ", allStation=" + allStation + "]";
    }

    @Override
    public int compareTo(Line o) {
        return this.getLineCode().compareTo(o.getLineCode());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allStation == null) ? 0 : allStation.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
        result = prime * result + ((lineName == null) ? 0 : lineName.hashCode());
        result = prime * result + ((startLineTime == null) ? 0 : startLineTime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Line other = (Line) obj;
        if (allStation == null) {
            if (other.allStation != null) {
                return false;
            }
        } else if (!allStation.equals(other.allStation)) {
            return false;
        }
        if (direction == null) {
            if (other.direction != null) {
                return false;
            }
        } else if (!direction.equals(other.direction)) {
            return false;
        }
        if (lineCode == null) {
            if (other.lineCode != null) {
                return false;
            }
        } else if (!lineCode.equals(other.lineCode)) {
            return false;
        }
        if (lineName == null) {
            if (other.lineName != null) {
                return false;
            }
        } else if (!lineName.equals(other.lineName)) {
            return false;
        }
        if (startLineTime == null) {
            if (other.startLineTime != null) {
                return false;
            }
        } else if (!startLineTime.equals(other.startLineTime)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        return true;
    }

}
