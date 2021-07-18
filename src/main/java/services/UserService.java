package services;

import dao.DataBaseDao;
import entity.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    /**
     * 用户插入接口
     * @param user 前端传回的User对象
     * @return 0 或大于0的数
     */
    public static int insert(User user){
        String sql = "insert into user values(?,?,?,?,date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?)";
        Object[] params = {user.getUser_id(), user.getUser_name(),user.getUser_pwd(), user.getUser_sex(), user.getUser_birthday(), user.getUser_identity_code(), user.getUser_email(), user.getUser_tel(), user.getUser_address(), user.getUser_status()};
        return DataBaseDao.execute(sql, params);
    }

    /**
     * 查询页面数据的总条数和页数
     * @param count 每页数据条数
     * @param kwd 查询的用户名的关键字
     * @return 返回页面数据的总条数和页数的数组arr
     */
    public static int[] totalPage(int count, String kwd){
        int[] arr = {0, 1}; //arr[0]代表总数，arr[1]代表总页数
        String sql = null;
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(kwd != null){
                sql = "select count(*) from user where user_name like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + kwd + "%");
            }else{
                sql = "select count(*) from user";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                arr[0] = rs.getInt(1);
                if(arr[0] % count == 0){
                    arr[1] = arr[0] / count;
                }else{
                    arr[1] = arr[0] / count + 1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DataBaseDao.closeAll(rs,ps,con);
        }
        return arr;
    }

    /**
     * 查询所有的用户 可以带关键字
     * @param cpage 当前页数
     * @param count 每页数据条数
     * @param kwd 搜索用户名的关键字
     * @return 返回由User组成的列表
     */
    public static ArrayList<User> selectAll( int cpage, int count, String kwd){
        ArrayList<User> list = new ArrayList<>();
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {
            if(kwd != null){
                sql = "select * from user where user_name like ? order by user_birthday desc limit ?, ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%"+kwd+"%");
                ps.setInt(2, (cpage - 1) * count);
                ps.setInt(3, count);
            }else{
                sql = "select * from user order by user_birthday desc limit ?, ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, (cpage - 1) * count);
                ps.setInt(2, count);
            }

            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10)
                );
                list.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DataBaseDao.closeAll(rs, ps, con);
        }
        System.out.println("返回userlist！");
        return list;
    }

    /**
     * 通过用户的user_id 查询user信息
     * @param user_id 用户的user_id
     * @return 返回User对象
     */
    public static User selectById(String user_id){
        User user = null;
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,user_id);
            rs = ps.executeQuery();
            while(rs.next()){
                user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DataBaseDao.closeAll(rs, ps, con);
        }
        return user;
    }
}
