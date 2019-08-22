import java.sql.*;
import java.util.ArrayList;

public class SqlCon {

    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String serverName = "localhost";
    private String database = "test";
    private String userName = "root";
    private String password = "Osc123456";

    public SqlCon() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + database + "?useSSL=false", userName, password);

        }catch (SQLException e) {
            System.out.println("Connection Fail...");
            e.printStackTrace();
        }
    }

    //받은 정보를 저장
    public void insert(ArrayList<Data> dataList){
        String sql = "INSERT INTO server VALUES (0,?,?)";

        for(Data item : dataList){
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, item.getNumber());
                pstmt.setString(2, item.getTime());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("SQLException Occured...");
                e.printStackTrace();
            }
        }
    }
}
