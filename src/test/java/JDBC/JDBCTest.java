package JDBC;

import dao.DataBaseDao;
import entity.User;
import services.UserService;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        User user = new User(
                "admin",
                "向亚欣",
                "123",
                "T",
                "20001022",
                null,
                "1010234469@qq.com",
                "18674420953",
                "慈利县零阳镇环城南路",
                1);
        int count = UserService.insert(user);
        System.out.println(count);
    }
}
