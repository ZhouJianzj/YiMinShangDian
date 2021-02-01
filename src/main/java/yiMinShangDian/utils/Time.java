package yiMinShangDian.utils;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName Time
 * @Description TODO
 * @Author
 * @Date 2021/2/1
 **/
@Setter
@Getter
public  class Time implements Runnable {
    private String time;
    private  JLabel jLabel;
    public Time(){

    }
    public Time(JLabel jLabel){
        this.jLabel = jLabel;
    }
    @Override
    public void run() {
        while(true) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
            time = simpleDateFormat.format(date);
            jLabel.setText(time);
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Time()).start();
    }
}
