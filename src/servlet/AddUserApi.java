package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddUserApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //Map<String,String[]> ps = request.getParameterMap();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取客户端POST参数的值
        String user = req.getParameter("user");
        String pswd = req.getParameter("pswd");
        resp.setStatus(200);
        PrintWriter pw=resp.getWriter(); //输出响应信息到客户端
        pw.write("<html><body><p>登录成功！\n你输入的用户名为："+user+"\n你输入的密码为："+pswd+"</p></body></html>");
        pw.close();
    }
}
