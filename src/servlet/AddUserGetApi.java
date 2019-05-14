package servlet;

import bean.User;
import dao.UserDaoImpl;
import weChat.SendWX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddUserGetApi extends HttpServlet {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //http://localhost:8080/hanmoWeb_war_exploded/AddUserGetApi?user=jun&tel=123

        resp.setHeader("Access-Control-Allow-Origin", "*");//允许跨域
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取客户端get参数的值
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        String sec = req.getParameter("sec");
        String ip = getIpAddr(req);
        String time = df.format(new Date());
        /*if (name.equals("") || name == null) {
            name = tel;
        }*/
        name = tel;
        resp.setStatus(200);
        PrintWriter pw = resp.getWriter(); //输出响应信息到客户端
        if (addUser(name, tel, sec, ip, time)) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    new SendWX().send(tel,sec);
                }
            }.start();
            pw.write("<html><body><p>添加成功</p></body></html>");
            pw.close();
        } else {
            pw.write("<html><body><p>添加失败</p></body></html>");
            pw.close();
        }

    }

    private boolean addUser(String name, String tel, String sec, String ip, String time) {

        User user = new User();
        user.userTel = tel;
        user.userName = name;
        user.userSec = sec;
        user.userIp = ip;
        user.userTime = time;
        List<User> l = new ArrayList<>();
        l.add(user);
        int[] i = UserDaoImpl.getInstance().create(l);
        if (i != null && i.length == 1) {
            System.out.println("注册成功===" + user.toString());
            return true;
        } else {
            System.out.println("=========注册失败========");
            return false;

        }
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }
}
