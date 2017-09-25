package com.heal.framework.foundation;

import com.heal.framework.test.RunTestSuite;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

/**
 * Created by vahanmelikyan on 9/13/2017.
 */
public class DBConnector {
    private static Logger logger = LoggerFactory.getLogger(DBConnector.class);

    private Connection connection;
    private String db;
    private String params;
    private String queryName;
    private String dbquery;
    public DBConnector(){

    }

    public void getConnection(String env) throws Exception{
        getConnection(new DBInfo(env));
    }

    public void getConnection(DBInfo dbInfo) throws Exception{
        getConnection(dbInfo.getUser(), dbInfo.getPassword());
    }

    public void getConnection(String user, String password) throws Exception{
        Properties props = new Properties();
        props.setProperty("user", RunTestSuite.getCryptography().decrypt(user));
        props.setProperty("password",RunTestSuite.getCryptography().decrypt(password));
        connection = DriverManager.getConnection(RunTestSuite.getCryptography().decrypt(DBInfo.url), props);
    }

    public void close(){
        try{
            connection.close();
        }catch (Exception e){

        }
    }

    public DBConnector useDB(String db){
        this.db = db;
        return this;
    }

    public DBConnector param(String param){
        this.params = param;
        return this;
    }

    public void query(String queryName){
        this.queryName = queryName;
        this.dbquery = DbQuery.getQuery(queryName);

        try{
            processResult();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception e){

            }

        }
    }


    private void processResult() throws SQLException {

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(dbquery);

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            int row = 0;
            String columnName;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    columnName = rs.getMetaData().getColumnName(i);
                    DBVariable.setCellValue(row, columnName, rs.getString(i));
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

        public static final String url = "yh8qGlyAh10bcROOJc9rzdX8dxmF6qPHMZnmDGf30459hwUgKQzD4gGagxqrMJ62JB8bQBOCBI48siahdXJsqJKaqhJTtzTRI1GSJrmuMVPqGBQkAMvK9eR3VxE/FQikcLim3S0Sj3H3AOk6IjNhHvYaIKaAZVoBu0V32ZXT6g+3YCjUEVaaRy6pVrzY81HQKiGDf+3tQBVZnokY1A6AdA==";
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
                    this.dbName = "9tuK+DsSlGObLMVC0hz8UdYDahQKhZyJ";
                    this.user = "1I/5pl+5kOW/b83UnfCcnA==";
                    this.password = "ogwXj36MMEbbH195sh+57L0vNEGB5MCpi1tJOLszWB4W4Y1knFewZQ==";
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

    @Test
    public void testConnection()throws Exception{

        DBConnector connector = new DBConnector();
        connector.getConnection("qa");
        connector.query("cancel_reason");
        connector.close();
        System.out.print(DBVariable.getCellValue("id"));

    }
}
