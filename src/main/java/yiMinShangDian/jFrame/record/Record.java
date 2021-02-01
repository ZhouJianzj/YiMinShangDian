package yiMinShangDian.jFrame.record;

import yiMinShangDian.entity.recordEntity;
import yiMinShangDian.utils.Time;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName Record
 * @Description TODO
 * @Author ZhouJian
 * @Date 2021/2/1
 **/
public class Record extends JFrame {
    JButton enter = new JButton("提交");
    JButton reset = new JButton("清除");
    JLabel name = new JLabel("姓名：");
    JLabel debt = new JLabel("赊账金额：");
    JLabel desc = new JLabel("赊账说明：");
    JLabel date = new JLabel("赊账时间：");
    JTextField nameText = new JTextField(22);
    JTextField debtText = new JTextField(22);
    JTextArea descText = new JTextArea("写明赊账原因，以免不必要的纠纷！",22,22);
    JLabel dateText = new JLabel();
    recordEntity record = new recordEntity();
    public Record (){
        setTitle("记账");
        setBounds(188,188,292,659);
        Container contentPane = getContentPane();
        setLayout(new FlowLayout());

        contentPane.add(name);
        contentPane.add(nameText);
        contentPane.add(debt);
        contentPane.add(debtText);
        contentPane.add(desc);
        contentPane.add(descText);
        contentPane.add(date);
        contentPane.add(dateText);
        contentPane.add(reset);
        contentPane.add(enter);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        Time time = new Time(dateText);
        new Thread(time).start();

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //实例创建放在上面是为了避免创建多个对象导致堆内存堆积
                //捕获输入信息传递给实例
                System.out.println("按钮事件执行");
                record.setName(nameText.getText());
                record.setDebt(Double.parseDouble(debtText.getText()));
                record.setDesc(descText.getText());
                record.setDate(dateText.getText());
                System.out.println(dateText.getText() + "设置实例对象属性成功");
                //存数据了
                new yiMinShangDian.imple.impRecord.Record(record).enter();
                System.out.println("调用存数据方法成功");
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                debtText.setText("");
                descText.setText("");
                record.setName(null);
                record.setDebt(null);
                record.setDesc(null);
                record.setDate(null);
            }
        });
    }

    public static void main(String[] args) {
        new Record();
    }
}
