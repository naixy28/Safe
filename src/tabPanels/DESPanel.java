package tabPanels;

import org.apache.commons.codec.binary.Base64;
import security.DES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.Key;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class DESPanel extends JPanel{

    private final JTextField desKey;
    private final JTextArea desPlainArea;
    private final JTextArea desCipherArea;
    public DESPanel() {
        this.setLayout(new FlowLayout());
        JButton desCreateKey= new JButton("生成DES密钥");
        desCreateKey.addActionListener(this::desCreateKey_Click);
        this.add(desCreateKey);
        JLabel desLabel= new JLabel("密钥:");
        this.add(desLabel);
        desKey = new JTextField("",20);
        this.add(desKey);
        JLabel desPlainLabel= new JLabel("请输入明文:");
        this.add(desPlainLabel);
        desPlainArea = new JTextArea(4,25);
        desPlainArea.setLineWrap(true);
        this.add(desPlainArea);
        JLabel desCipherLabel= new JLabel("请输入密文:");
        this.add(desCipherLabel);
        desCipherArea = new JTextArea(4,25);
        desCipherArea.setLineWrap(true);
        this.add(desCipherArea);
        JButton desEncrypt= new JButton("加密");
        desEncrypt.addActionListener(this::desEncrypt_Click);
        this.add(desEncrypt);
        JButton desDecrypt= new JButton("解密");
        desDecrypt.addActionListener(this::desDecrypt_Click);
        this.add(desDecrypt);
    }
    private void desDecrypt_Click(ActionEvent actionEvent) {
        Key desKeyKey = DES.parseKey(desKey.getText());
        DES des=new DES(desKeyKey);
        String cipherText = desCipherArea.getText();
        String plainText = des.decrypt(Base64.decodeBase64(cipherText.getBytes()));
        desPlainArea.setText(plainText);
    }

    private void desEncrypt_Click(ActionEvent actionEvent) {
        Key desKeyKey = DES.parseKey(desKey.getText());
        DES des=new DES(desKeyKey);
        String plainText = desPlainArea.getText();
        String cipherText = new String(Base64.encodeBase64(des.encrypt(plainText)));
        desCipherArea.setText(cipherText);
    }

    private void desCreateKey_Click(ActionEvent actionEvent) {
        desKey.setText(DES.createDESKey());
    }
}
