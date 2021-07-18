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
    public static int insert(User user){
        String sql = "insert into user values(?,?,?,?,date_format(?, '%Y-%m-%d'), ?, ?, ?, ?, ?)";
        Object[] params = {user.getUser_id(), user.getUser_name(),user.getUser_pwd(), user.getUser_sex(), user.getUser_birthday(), user.getUser_identity_code(), user.getUser_email(), user.getUser_tel(), user.getUser_address(), user.getUser_status()};
        return DataBaseDao.execute(sql, params);
    }

    public static int[] totalPage(int count){
        int[] arr = {0, 1}; //arr[0]代表总数，arr[1]代表总页数
        String sql = "select count(*) from user";
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
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

    public static ArrayList<User> selectAll( int cpage, int count){
        String sql = "select * from user order by user_birthday desc limit ?, ?";
        ArrayList<User> list = new ArrayList<>();
        Connection con = DataBaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, (cpage - 1) * count);
            ps.setInt(2, count);
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

}
