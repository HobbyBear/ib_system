package com.bus.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.bus.dao.impl.BusDaoImpl;
import com.bus.dao.impl.LineDaoImpl;
import com.bus.dao.impl.RoleDaoImpl;
import com.bus.dao.impl.SchedulingDaoImpl;
import com.bus.dao.impl.StationDaoImpl;
import com.bus.dao.impl.UserDaoImpl;

public class DaoFactory {
	private static String userName;
	private static String password;
	private static String ip;
	private static String port;
	private static String dbName;
	private static String connectString = "";
	static {
		try {
			InputStream configInputStream = new FileInputStream(
					"config/config.xml");
			Properties properties = new Properties();
			properties.loadFromXML(configInputStream);
			userName = properties.getProperty("userName");
			password = properties.getProperty("password");
			ip = properties.getProperty("ip");
			port = properties.getProperty("port");
			dbName = properties.getProperty("dbName");
			connectString = "jdbc:mysql://" + ip + ":" + port + "/" + dbName+"?useUnicode=true&characterEncoding=UTF-8";
			Class.forName("com.mysql.jdbc.Driver");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnect() {
		try {
			return DriverManager.getConnection(connectString, userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static BusDao getBusDao() {
		return new BusDaoImpl();
	}

	public static LineDao getLineDao() {
		return new LineDaoImpl();
	}

	public static SchedulingDao getSchedulingDao() {
		return new SchedulingDaoImpl();
	}
	
	public static StationDao getStationDao() {
		return new StationDaoImpl();
	}
	
	public static RoleDao getRoleDao() {
		return new RoleDaoImpl();
	}
}
