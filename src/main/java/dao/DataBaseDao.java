package dao;

import java.sql.*;

public class DataBaseDao {
    private static String url = "jdbc:mysql://localhost:3306/mymall";
    private static String user = "root";
    private static String pwd = "root";
    public static Connection con = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("获取连接成功！");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    /**
     *增 删 改 接口
     * @param sql 放进PreparedStatement 的 sql语句(带参sql）
     * @param params 语句中的参数组成的数组
     * @return count （1）SQL数据操作语言（DML）语句的行计数或（2）0不返回的SQL语句
     */
    public static int execute(String sql, Object []params){
        int count = 0;
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
                ps.setObject(i + 1, params[i]);
            }
            count = ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(count > 0){
                System.out.println("增删改语句执行成功");
            }else{
                System.out.println("增删改语句执行失败");
            }
            DataBaseDao.closeAll(null, ps, con);
        }
        return count;
    }

    /**
     * 资源关闭接口：结果集、预备语句、连接
     * @param rs 需要关闭的结果集
     * @param ps 需要关闭的预编译sql对象
     * @param con 需要关闭的数据库连接
     */
    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection con){
        try{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
