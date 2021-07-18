package servlet;

import entity.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("Text/html; charset=utf-8");
        int cpage = 1;//当前页
        int count = 5;//每页5条

        //cp:用户指定页面参数
        String cp = req.getParameter("cp");
        String kwd = req.getParameter("kwd");

        if(cp != null){
            cpage = Integer.parseInt(cp);
        }

        //获取数据总条数与总页数
        int[] arr = UserService.totalPage(count, kwd);

        req.setAttribute("tsum", arr[0]);//总条数
        req.setAttribute("tpage", arr[1]);//总页数
        req.setAttribute("cpage", cpage);
        System.out.println("kwd = " + kwd);
        if(kwd != null){
            req.setAttribute("searchParams", "&kwd=" + kwd);
        }
        ArrayList<User> userList = UserService.selectAll(cpage, count, kwd);
        req.setAttribute("userlist", userList);
        req.getRequestDispatcher("admin_user.jsp").forward(req, resp);
    }
}
