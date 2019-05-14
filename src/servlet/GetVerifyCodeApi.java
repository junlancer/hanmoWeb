package servlet;

import chuanglan.sms.SmsSend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GetVerifyCodeApi extends HttpServlet {
    public static ArrayList<String> codeList = new ArrayList<>(10);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        resp.setContentType("text/html;charset=utf-8");
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(verifyCode);
        if (codeList.size() > 100) {
            codeList.clear();
            codeList = new ArrayList<>(10);
        }
        codeList.add(verifyCode);
        String tel = req.getParameter("tel");
        //new SmsSend().send(verifyCode,tel);
    }
}
