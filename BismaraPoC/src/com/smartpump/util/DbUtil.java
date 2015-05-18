package com.smartpump.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DbUtil {
	
	private DataSource dataSource;
	 
	public DataSource getDataSource() {
		return dataSource;
	}
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	 
	public void initialize(){
		DataSource dataSource = getDataSource();
	try {
		Connection connection = (Connection) dataSource.getConnection();
		Statement statement = (Statement) connection.createStatement();
		/*statement.executeUpdate("CREATE TABLE PERSON (ID INTEGER IDENTITY, NAME VARCHAR(50), EMAIL VARCHAR(100))");*/
		statement.close();
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	}
}
