package tabPanels;

import security.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class MD5Panel extends JPanel {
    private final JTextField md5Key;
    private final JTextArea md5ValueArea;
    public MD5Panel(){
        this.setLayout(new FlowLayout());
        JButton md5Create= new JButton("生成MD5");
        md5Create.addActionListener(this::md5Create_Click);
        this.add(md5Create);
        JLabel md5Label= new JLabel("原始数据:");
        this.add(md5Label);
        md5Key = new JTextField("",20);
        this.add(md5Key);
        JLabel md5Value= new JLabel("MD5值:");
        this.add(md5Value);
        md5ValueArea = new JTextArea(1,25);
        md5ValueArea.setLineWrap(true);
        this.add(md5ValueArea);
    }

    private void md5Create_Click(ActionEvent actionEvent) {
        String md5KeyGet =  md5Key.getText();
        String md5Text = MD5.createMD5(md5KeyGet);
        md5ValueArea.setText(md5Text);
    }
}
