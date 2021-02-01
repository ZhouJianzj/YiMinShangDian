package yiMinShangDian.jFrame.SelectFunction;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName SelectFunction
 * @Description TODO
 * @Author zhoujian
 * @Date 2021/1/31
 **/
public class SelectFunction extends JFrame {
    JButton record = new JButton("记账");
    JButton delete = new JButton("还账");
    JButton cleanDebt = new JButton("结清全部账款！");
    JButton recordAndDelete = new JButton("查看记、还账记录");
    JButton lookRecord = new JButton("查看记账本");
    JButton lookDelete = new JButton("查看还账本");
    public SelectFunction(){
        setTitle("功能选择");
        setBounds(683,350,222,222);
        setLayout(new FlowLayout());
        Container contentPane = getContentPane();
        contentPane.add(delete);
        contentPane.add(record);
        contentPane.add(lookRecord);
        contentPane.add(lookDelete);
        contentPane.add(recordAndDelete);
        contentPane.add(cleanDebt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        new SelectFunction();
    }
}
