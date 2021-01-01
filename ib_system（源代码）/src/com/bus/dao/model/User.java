/**  
 * @Title: User.java
 * @Package com.bus.model
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0  
 */
package com.bus.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: User
 * @Description: 用户实体
 * @author yuqingming
 * @date 2020年6月11日
 * @since JDK 1.8
 */
public class User implements Serializable, Comparable<User> {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
	/**
	 * 用户编号
	 */
	private String code;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 角色
	 */
	private String role;
	
	/**
	 * 驾龄
	 */
	private Integer driving;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * @Fields authorityList : 权限列表
	 */
	private List<String> authorityList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getDriving() {
		return driving;
	}

	public void setDriving(Integer driving) {
		this.driving = driving;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	@Override
	public String toString() {
		return "User [code=" + code + ", loginName=" + loginName + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", idCard=" + idCard + ", role=" + role + ", driving=" + driving + ", status="
				+ status + "]";
	}

	/* (非 Javadoc)
	 * 
	 * 
	 * @param o
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(User o) {
		return this.getCode().compareTo(o.getCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((driving == null) ? 0 : driving.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (driving == null) {
			if (other.driving != null) {
				return false;
			}
		} else if (!driving.equals(other.driving)) {
			return false;
		}
		if (idCard == null) {
			if (other.idCard != null) {
				return false;
			}
		} else if (!idCard.equals(other.idCard)) {
			return false;
		}
		if (loginName == null) {
			if (other.loginName != null) {
				return false;
			}
		} else if (!loginName.equals(other.loginName)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
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
