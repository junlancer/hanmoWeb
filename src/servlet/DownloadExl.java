package servlet;

import utils.ReadExcelFromDB;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class DownloadExl extends HttpServlet {
    private String filepath = "." + File.separator + "user.xls";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        try {
            new ReadExcelFromDB().dbToExl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setHeader("Content-Disposition","attachment;filename="+"user.xls");
        String mineType = this.getServletContext().getMimeType("user.xls");
        ServletOutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(filepath);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer,0,len);
        }
        in.close();
    }


}
