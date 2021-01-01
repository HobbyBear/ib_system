/**  
 * @Title: Scheduling.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0  
 */
package com.bus.dao.model;

import java.io.Serializable;

/**
 * @ClassName: Scheduling
 * @Description: 排班实体
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class Scheduling implements Serializable, Comparable<Scheduling>{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public Scheduling() {
		super();
	}
	
	/**
	 * 唯一值 排班编号
	 */
	private String code;
	
	/**
	 * 线路编号
	 */
	private String lineCode;
	
	/**
	 * 线路名称
	 */
	private String lineName;
	
	/**
	 * 车牌号
	 */
	private String busCode;
	
	/**
	 * 趟次
	 */
	private String tcNumber;
	
	/**
	 * 每趟时间
	 */
	private String tcTime;
	
	/**
	 * 司机编号
	 */
	private String userCode;
	
	/**
	 * 司机姓名
	 */
	private String userName;
	
	/**
	 * 始发站点
	 */
	private String startStation;
	
	/**
	 * 终点站点
	 */
	private String endStation;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}

	public String getTcTime() {
		return tcTime;
	}

	public void setTcTime(String tcTime) {
		this.tcTime = tcTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	@Override
	public String toString() {
		return "Scheduling [code=" + code + ", lineCode=" + lineCode + ", lineName=" + lineName + ", busCode=" + busCode
				+ ", tcNumber=" + tcNumber + ", tcTime=" + tcTime + ", userCode=" + userCode + ", userName=" + userName
				+ ", startStation=" + startStation + ", endStation=" + endStation + "]";
	}

	@Override
	public int compareTo(Scheduling o) {
		return this.getCode().compareTo(o.getCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busCode == null) ? 0 : busCode.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((endStation == null) ? 0 : endStation.hashCode());
		result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
		result = prime * result + ((lineName == null) ? 0 : lineName.hashCode());
		result = prime * result + ((startStation == null) ? 0 : startStation.hashCode());
		result = prime * result + ((tcNumber == null) ? 0 : tcNumber.hashCode());
		result = prime * result + ((tcTime == null) ? 0 : tcTime.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Scheduling other = (Scheduling) obj;
		if (busCode == null) {
			if (other.busCode != null) {
				return false;
			}
		} else if (!busCode.equals(other.busCode)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (endStation == null) {
			if (other.endStation != null) {
				return false;
			}
		} else if (!endStation.equals(other.endStation)) {
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
		if (startStation == null) {
			if (other.startStation != null) {
				return false;
			}
		} else if (!startStation.equals(other.startStation)) {
			return false;
		}
		if (tcNumber == null) {
			if (other.tcNumber != null) {
				return false;
			}
		} else if (!tcNumber.equals(other.tcNumber)) {
			return false;
		}
		if (tcTime == null) {
			if (other.tcTime != null) {
				return false;
			}
		} else if (!tcTime.equals(other.tcTime)) {
			return false;
		}
		if (userCode == null) {
			if (other.userCode != null) {
				return false;
			}
		} else if (!userCode.equals(other.userCode)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}
	
}
