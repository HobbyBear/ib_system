/**  
 * @Title: Station.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0  
 */
package com.bus.dao.model;

import java.io.Serializable;

/**
 * @ClassName: Station
 * @Description: 站点实体
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class Station implements Serializable, Comparable<Station> {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public Station() {
		super();
	}
	
	/**
	 * 站点编号
	 */
	private String stationCode;
	
	/**
	 * 站点名称
	 */
	private String stationName;
	
	/**
	 * 经度 
	 */
	private String longitude;
	
	/**
	 * 纬度   
	 */
	private String latitude;
	
	/**
	 * 所在区域
	 */
	private String region;
	
	/**
	 * 所在街道
	 */
	private String street;

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Station [stationCode=" + stationCode + ", stationName=" + stationName + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", region=" + region + ", street=" + street + "]";
	}

	@Override
	public int compareTo(Station o) {
		return this.getStationCode().compareTo(o.getStationCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((stationCode == null) ? 0 : stationCode.hashCode());
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Station other = (Station) obj;
		if (latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!latitude.equals(other.latitude)) {
			return false;
		}
		if (longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!longitude.equals(other.longitude)) {
			return false;
		}
		if (region == null) {
			if (other.region != null) {
				return false;
			}
		} else if (!region.equals(other.region)) {
			return false;
		}
		if (stationCode == null) {
			if (other.stationCode != null) {
				return false;
			}
		} else if (!stationCode.equals(other.stationCode)) {
			return false;
		}
		if (stationName == null) {
			if (other.stationName != null) {
				return false;
			}
		} else if (!stationName.equals(other.stationName)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		return true;
	}
	
}
