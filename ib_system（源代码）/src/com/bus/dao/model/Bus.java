/**
 * @Title: Bus.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0
 */
package com.bus.dao.model;

import java.io.Serializable;

/**
 * @ClassName: Bus
 * @Description: 车辆实体
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class Bus implements Serializable, Comparable<Bus> {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public Bus() {
        super();
    }

    /**
     * 车牌号
     */
    private String busLicense;

    /**
     * 车辆编号
     */
    private String busCode;

    /**
     * 车型
     */
    private String busType;

    /**
     * 车辆状态
     */
    private String busStatus;

    /**
     * 启用时间
     */
    private String startTime;


    public String getBusLicense() {
        return busLicense;
    }


    public void setBusLicense(String busLicense) {
        this.busLicense = busLicense;
    }


    public String getBusCode() {
        return busCode;
    }


    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }


    public String getBusType() {
        return busType;
    }


    public void setBusType(String busType) {
        this.busType = busType;
    }


    public String getBusStatus() {
        return busStatus;
    }


    public void setBusStatus(String busStatus) {
        this.busStatus = busStatus;
    }


    public String getStartTime() {
        return startTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Bus [busLicense=" + busLicense + ", busCode=" + busCode + ", busType=" + busType + ", busStatus="
                + busStatus + ", startTime=" + startTime + "]";
    }

    @Override
    public int compareTo(Bus o) {
        return this.getBusCode().compareTo(o.getBusCode());
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((busCode == null) ? 0 : busCode.hashCode());
        result = prime * result + ((busLicense == null) ? 0 : busLicense.hashCode());
        result = prime * result + ((busStatus == null) ? 0 : busStatus.hashCode());
        result = prime * result + ((busType == null) ? 0 : busType.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        Bus other = (Bus) obj;
        if (busCode == null) {
            if (other.busCode != null) {
                return false;
            }
        } else if (!busCode.equals(other.busCode)) {
            return false;
        }
        if (busLicense == null) {
            if (other.busLicense != null) {
                return false;
            }
        } else if (!busLicense.equals(other.busLicense)) {
            return false;
        }
        if (busStatus == null) {
            if (other.busStatus != null) {
                return false;
            }
        } else if (!busStatus.equals(other.busStatus)) {
            return false;
        }
        if (busType == null) {
            if (other.busType != null) {
                return false;
            }
        } else if (!busType.equals(other.busType)) {
            return false;
        }
        if (startTime == null) {
            if (other.startTime != null) {
                return false;
            }
        } else if (!startTime.equals(other.startTime)) {
            return false;
        }
        return true;
    }

}
