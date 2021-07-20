package servlet;

import entity.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/admin_dousermodify")
public class DoUserModify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("Text/html; charset=utf-8");

        String user_id = req.getParameter("user_id");
        User user = UserService.selectById(user_id);
        req.setAttribute("user", user);
        req.setAttribute("cpage", req.getParameter("cpage"));
        req.getRequestDispatcher("admin_userupdate.jsp").forward(req,resp);
    }
}
