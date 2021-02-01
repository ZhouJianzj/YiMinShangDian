package yiMinShangDian.jFrame.login;


import yiMinShangDian.entity.login;
import yiMinShangDian.imple.impCheckedLoading.impChexkedLoading;
import yiMinShangDian.jFrame.SelectFunction.SelectFunction;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * @ClassName login
 * @Description TODO
 * @Author ZhouJian
 * @Date 2021/1/31
 **/
public class Login extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JLabel title = new JLabel("益民商店");
    JLabel titleUserName = new JLabel("用户名：");
    JTextField UserName = new JTextField(20);
    JLabel titlePassword = new JLabel("密码：");
    JPasswordField password = new JPasswordField(20);
    JButton loading = new JButton("登录");
    JButton reset   = new JButton("重置");
    JPanel right = new JPanel();
    JLabel jLabelRight = new JLabel();
    SystemTray systemTray;
    TrayIcon trayIcon;

    login use ;
    public Login(){
        setTitle("益民商店");
        setSize(1366,705);
//        setResizable(false);
        jPanel.add(title);
        jPanel.add(titleUserName);
        jPanel.add(UserName);
        jPanel.add(titlePassword);
        jPanel.add(password);
        jPanel.add(loading);
        jPanel.add(reset);
        jPanel.add(right);
        right.add(jLabelRight);
        title.setFont(new Font("华文楷体",Font.CENTER_BASELINE,48));
        SpringLayout.Constraints cTitle = springLayout.getConstraints(title);
        cTitle.setX(Spring.constant(300));
        cTitle.setY(Spring.constant(88));

        /*
        1、参照上一个组件的写法就是 先获取到需要参照组件的边 组件约束getConstraint（SpringLayOut.方向）
        2、在去设置需要参照的组件里面设置setConstraint(SpringLayOut.自己需要参照的边，Spring.sum(被参照的边，Spring.constant（相离的距离))
        * */
        titleUserName.setFont(new Font("楷体",Font.BOLD,20));
        SpringLayout.Constraints cTitleUserName = springLayout.getConstraints(titleUserName);
        cTitleUserName.setX(Spring.constant(260));
        cTitleUserName.setY(Spring.constant(200));
        titlePassword.setFont(new Font("楷体",Font.BOLD,20));
        SpringLayout.Constraints cTtilePassword = springLayout.getConstraints(titlePassword);
        cTtilePassword.setX(Spring.constant(260));
        cTtilePassword.setY(Spring.constant(250));
        SpringLayout.Constraints cUseName = springLayout.getConstraints(UserName);
        cUseName.setY(Spring.constant(200));
        cUseName.setX(Spring.constant(340));
        SpringLayout.Constraints cPassword = springLayout.getConstraints(password);
        cPassword.setY(Spring.constant(250));
        cPassword.setX(Spring.constant(340));
        SpringLayout.Constraints cLoading = springLayout.getConstraints(loading);
        cLoading.setY(Spring.constant(300));
        Spring cPasswordConstraint = cPassword.getConstraint(SpringLayout.EAST);
        cLoading.setConstraint(SpringLayout.EAST,Spring.sum(cPasswordConstraint,Spring.constant(0)));
        SpringLayout.Constraints cReset = springLayout.getConstraints(reset);
        cReset.setY(Spring.constant(300));
        Spring cPasswordConstraintwest = cPassword.getConstraint(SpringLayout.WEST);
        cReset.setConstraint(SpringLayout.WEST,Spring.sum(cPasswordConstraintwest,Spring.constant(0)));
//设置右边一般为图片
//        SpringLayout.Constraints cRight = springLayout.getConstraints(right);
//        cRight.setX(Spring.constant(683));
//        jLabelRight.setIcon(new ImageIcon("yiMinShangDian/pic/pic1.png"));

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        loading.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //检测登录的方法
                use = new login();

                use.setUserName(UserName.getText());
                use.setPassword(String.valueOf(password.getPassword()));
                if (use.getUserName().equals("") ){
                    JOptionPane.showMessageDialog(jPanel,"请输入用户名！");
                }else if (use.getPassword().equals("")){
                    JOptionPane.showMessageDialog(jPanel,"请输入用户密码！");
                }else if (new impChexkedLoading().checkedLoading(use)){
                    JOptionPane.showMessageDialog(jPanel,"登录成功！");
                    Login.this.dispose();
                    new SelectFunction();
                }else{
                    JOptionPane.showMessageDialog(jPanel,"账户密码错误");
                }

            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //清除数据
                password.setText("");
                UserName.setText("");
            }
        });
        //        添加系统托盘 tray
        if (SystemTray.isSupported()){
            systemTray = SystemTray.getSystemTray();
            // 系统托盘图标的大小是16 * 16 的
            URL resource = Login.class.getClassLoader().getResource("pic/pic3.jpg");
            trayIcon = new TrayIcon(new ImageIcon(resource).getImage());
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1){
                        Login.this.setExtendedState(JFrame.NORMAL);
                    }
                    Login.this.setVisible(true);
                }
            });
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}
