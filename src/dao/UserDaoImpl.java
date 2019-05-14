package dao;

import bean.User;
import utils.CloseUtil;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tb
 * @time 2018/8/14 下午5:08
 * @des 用户操作相关
 */
public class UserDaoImpl implements IDao<User> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public static UserDaoImpl getInstance() {
        return SingletonInstance.instance;
    }

    private static class SingletonInstance {
        private static final UserDaoImpl instance = new UserDaoImpl();
    }

    private UserDaoImpl() {
        connection = JdbcUtil.getConnection();
        String sql = "create table if not exists user" +
                "(userId int(10) auto_increment primary key," +
                "userName varchar(20) null," +
                "userTel varchar(20) null," +
                "userSec varchar(30) null," +
                "userIp varchar(50) null," +
                "userTime varchar(30) null," +
                "userFrom varchar(30) null," +
                "role int(5) default '0' not null," +
                "constraint userName unique (userName)" +
                ") DEFAULT CHARSET=utf8mb4";//支持emoji表情
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }

        /*List<User> list = read(null);
        if (list != null && list.size() > 0) {
            for (User u : list) {
                if (u.userName.equals("admin")) {
                    return;
                }
            }
        }
        //添加一个默认的管理员
        List<User> l = new ArrayList<>();
        User u = new User();
        u.userName = "admin";
        u.userTel = "15527070531";
        u.role = -1;
        u.userSec = "admin";
        u.userIp = "0.0.0.0";
        u.userTime = "2000-11-11 12:12:12";
        l.add(u);
        create(l);*/
    }

    @Override
    public int[] create(List<User> list) {
        String sql = "insert into user(userName,userTel,userSec,userIp,userTime,userFrom,role) values(?,?,?,?,?,?,?)";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (User user : list) {
                preparedStatement.setString(1, user.userName);
                preparedStatement.setString(2, user.userTel);
                preparedStatement.setString(3, user.userSec);
                preparedStatement.setString(4, user.userIp);
                preparedStatement.setString(5, user.userTime);
                preparedStatement.setString(6, user.userFrom);
                preparedStatement.setInt(7, user.role);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public List<User> read(Object o) {
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        String sql = "select * from user where userName=?";
        if (o == null) {
            sql = "select * from user";
        }
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            if (o != null) {
                preparedStatement.setString(1, String.valueOf(o));
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.userName = resultSet.getString("userName");
                user.userTel = resultSet.getString("userTel");
                user.userSec = resultSet.getString("userSec");
                user.userIp = resultSet.getString("userIp");
                user.userTime = resultSet.getString("userTime");
                user.userFrom = resultSet.getString("userFrom");
                user.userId = resultSet.getInt("userId");
                user.role = resultSet.getInt("role");
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public int[] update(List<User> list) {
        String sql = "update user set userTel=?,userSec=?,userIp=?,userTime=?,userFrom=?,role=? where userName=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (User user : list) {
                preparedStatement.setString(1, user.userTel);
                preparedStatement.setString(2, user.userSec);
                preparedStatement.setString(3, user.userIp);
                preparedStatement.setString(4, user.userTime);
                preparedStatement.setString(5, user.userFrom);
                preparedStatement.setInt(6, user.role);
                preparedStatement.setString(7, user.userName);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public void check(List<User> list) {
        String sql = "update user set role=? where userName=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (User user : list) {
                preparedStatement.setInt(1, user.role);
                preparedStatement.setString(2,user.userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
    }

    @Override
    public int[] delete(List<Object> list) {
        String sql = "delete from user where userName=?";
        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            preparedStatement = connection.prepareStatement(sql);
            for (Object o : list) {
                preparedStatement.setString(1, String.valueOf(o));
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;
    }


    @Override
    public List<User> list(int start, int count) {
        List<User> userList = new ArrayList<>();

        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            //String sql = "select * from user where userName=?";
            String sql = "select * from user order by userId desc limit ?,? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                User user = new User();
                user.userName = resultSet.getString("userName");
                user.userTel = resultSet.getString("userTel");
                user.userSec = resultSet.getString("userSec");
                user.userIp = resultSet.getString("userIp");
                user.userTime = resultSet.getString("userTime");
                user.userFrom = resultSet.getString("userFrom");
                user.userId = resultSet.getInt("userId");
                user.role = resultSet.getInt("role");
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return null;

    }

    @Override
    public int getAllCount() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = JdbcUtil.getConnection();
            }
            String sql = "SELECT COUNT(*) FROM user;";
            int rowCount = 0;
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }

            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(connection, preparedStatement);
        }
        return 0;

    }


}
