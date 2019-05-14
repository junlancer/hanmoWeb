package servlet;

import bean.User;
import dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PagingReadingServlet extends HttpServlet {
    private int start = 0;
    private int count = 20;
    private int flag;
    private List<User> userList = null;
    private int allCount;
    private int checkId;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        resp.setContentType("text/html;charset=utf-8");


        allCount = UserDaoImpl.getInstance().getAllCount();
        try {
            String admin = req.getParameter("admin");
            if (!admin.equals("admin")){
                return;
            }
            flag = Integer.parseInt(req.getParameter("flag"));
            start = Integer.parseInt(req.getParameter("start"));
            checkId = Integer.parseInt(req.getParameter("checkId"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数start时
        }

        if (flag == 1) {
            start = start + count;
        }
        if (flag == -1) {
            start = start - count;
        }

        if (start < 0) {
            start = 0;
        }

        if (count <= allCount && start > allCount - count) {
            start = allCount - count;
        }

        if (count > allCount) {
            start = 0;
        }

        userList = UserDaoImpl.getInstance().list(start, count);
        req.setAttribute("userList", userList);
        req.setAttribute("start", start);
        req.getRequestDispatcher("listUser.jsp").forward(req, resp);
    }
}
