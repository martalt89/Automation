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

    public void getConnection(String env) throws Exception{
        getConnection(new DBInfo(env));
    }

    public void getConnection(DBInfo dbInfo) throws Exception{
        getConnection(dbInfo.getDbName(), dbInfo.getUser(), dbInfo.getPassword());
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


    private class DBInfo{

        private String dbName = "";
        private String user = "";
        private String password = "";

        public DBInfo(String env){
            setDBInfo(env);
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private void setDBInfo(String env){

            switch (env.toLowerCase()){

                case "qa":
                    this.dbName = "";
                    this.user = "";
                    this.password = "";
                    break;
                case "dev":
                    this.dbName = "";
                    this.user = "";
                    this.password = "";
                    break;
                default:
                    break;
            }


        }
    }
}
