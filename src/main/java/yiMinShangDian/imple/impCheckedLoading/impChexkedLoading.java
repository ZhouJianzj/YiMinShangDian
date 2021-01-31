package yiMinShangDian.imple.impCheckedLoading;

import yiMinShangDian.entity.login;
import yiMinShangDian.serve.login.CheckedLoading;
import yiMinShangDian.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName impChexkedLoading
 * @Description TODO
 * @Author
 * @Date 2021/1/31
 **/
public class impChexkedLoading implements CheckedLoading {
    public boolean checkedLoading(login login) {
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select username, password from login";
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sql);
             while (resultSet.next()){
                 String userName = resultSet.getString("username");
                 String password = resultSet.getString("password");
                 if (userName.equals(login.getUserName())){
                     if (password.equals(login.getPassword())){

                         return true;
                     }
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            initJdbcUtil.closeConnection();
        }
        return  false;
    }
}
