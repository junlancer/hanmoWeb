package servlet;

import bean.User;
import dao.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckUserApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        String userName = req.getParameter("userName");

        boolean flag = false;
        List<User> list = UserDaoImpl.getInstance().read(userName);
        if (list != null && list.size() > 0 && userName.equals(list.get(0).userName)) {
            list.get(0).role = -1;
            int[] i = UserDaoImpl.getInstance().update(list);
            if (i != null && i.length == 1) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("修改成功===" + list.get(0).toString());
            RequestDispatcher dispatcher = req.getRequestDispatcher("PagingReadingServlet?admin=admin");
            dispatcher.forward(req, resp);
        } else {

        }
    }
}
