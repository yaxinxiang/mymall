package servlet;

import entity.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("Text/html; charset=utf-8");
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String address = req.getParameter("address");

        User user = new User(username, name, password, sex, birthday, null, email, tel,address, 1);
        System.out.println(user);
        int count = UserService.insert(user);
        System.out.println("count = "+ count);
        if(count > 0){
            resp.sendRedirect("admin_douserselect");
        }else{
            PrintWriter out = resp.getWriter();
            //写的有问题！！！！！
            out.write("<script>");
            out.write("alter<'用户添加失败‘>");
            out.write("location.herf = 'manage/admin_useradd.jsp");
            out.write("</script>");
        }
    }
}
