package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DbUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf8";
    private static String name = "root";
    private static String pwd = "12345";
    public Connection conn;
    public PreparedStatement pstmt;
    public ResultSet rs;

    static{
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getConnection(){
        try {
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(url, name, pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAll(){
        try {
            if(rs != null){
                rs.close();
            }
            if(pstmt != null){
                pstmt.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql,Object...o){
        int result = 0;
        try {
            getConnection();
            pstmt = conn.prepareStatement(sql);
            for(int i = 0 ; i < o.length; i++){
                pstmt.setObject((i+1), o[i]);
            }
            System.out.println(pstmt.toString());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeAll();
        }

        return result;
    }

    public void executeQuery(String sql,Object...o){
        try {
            getConnection();
            pstmt = conn.prepareStatement(sql);
            for(int i = 0 ; i < o.length; i++){
                pstmt.setObject((i+1), o[i]);
            }
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
