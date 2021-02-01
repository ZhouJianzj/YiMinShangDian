package yiMinShangDian.imple.impRecord;

import yiMinShangDian.serve.Record.record;
import yiMinShangDian.utils.JdbcUtil;
import yiMinShangDian.entity.recordEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName Record
 * @Description TODO
 * @Author
 * @Date 2021/2/1
 **/
public class Record implements record {
    private  recordEntity  record;
    public Record(){}
    public Record(recordEntity record){
        this.record = record;
        System.out.println("执行有参构造方法");
    }
    @Override
    public boolean enter() {
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement  preparedStatement = null;
        String name = record.getName();
        Double debt = record.getDebt();
        String desc = record.getDesc();
        String date = record.getDate();
        System.out.println("获取实例参数成功");
        StringBuffer sql =new StringBuffer("insert into record(name,debt,desc,date)values(?,?,?,?)");
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,debt);
            preparedStatement.setString(3,desc);
            preparedStatement.setString(4,date);
            System.out.println("设置？参数成功");
            boolean i = preparedStatement.execute();
            System.out.println(i+"记账成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            initJdbcUtil.closeConnection();
        }
        return false;
    }
}
