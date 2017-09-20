package com.heal.framework.foundation;

import com.heal.framework.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by vahanmelikyan on 9/13/2017.
 */
public class DBConnector {
    private static Logger logger = LoggerFactory.getLogger(DBConnector.class);

    private Connection connection;
    private String db;
    private String params;
    private String queryName;
    public DBConnector(){

    }


    public void getConnection(String url, String user, String password) throws Exception{
        connection = DriverManager.getConnection(url, user, password);
    }


    public DBConnector useDB(String db){
        this.db = db;
        return this;
    }

    public DBConnector param(String param){
        this.params = param;
        return this;
    }

    public DBResult query(String queryName){
        this.queryName = queryName;



        return null;
    }


    private void getResult() throws SQLException {

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(queryName);

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            int row = 0;
            String columnName;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    columnName = rs.getMetaData().getColumnName(i);
                    DBVariable.setValue(row, columnName, rs.getString(i));
                }
                row++;
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

}
