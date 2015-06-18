package tabPanels;

import org.apache.commons.codec.binary.Base64;
import security.AES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.Key;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class AESPanel extends JPanel {

    private final JTextField aesKey;
    private final JTextArea aesPlainArea;
    private final JTextArea aesCipherArea;
    public AESPanel() {
        this.setLayout(new FlowLayout());
        JButton aesCreateKey= new JButton("生成AES密钥");
        aesCreateKey.addActionListener(this::aesCreateKey_Click);
        this.add(aesCreateKey);
        JLabel aesLabel= new JLabel("密钥:");
        this.add(aesLabel);
        aesKey = new JTextField("",20);
        this.add(aesKey);
        JLabel aesPlainLabel= new JLabel("请输入明文:");
        this.add(aesPlainLabel);
        aesPlainArea = new JTextArea(4,25);
        aesPlainArea.setLineWrap(true);
        this.add(aesPlainArea);
        JLabel aesCipherLabel= new JLabel("请输入密文:");
        this.add(aesCipherLabel);
        aesCipherArea = new JTextArea(4,25);
        aesCipherArea.setLineWrap(true);
        this.add(aesCipherArea);
        JButton aesEncrypt= new JButton("加密");
        aesEncrypt.addActionListener(this::aesEncrypt_Click);
        this.add(aesEncrypt);
        JButton aesDecrypt= new JButton("解密");
        aesDecrypt.addActionListener(this::aesDecrypt_Click);
        this.add(aesDecrypt);
    }
    private void aesDecrypt_Click(ActionEvent actionEvent) {
        Key aesKeyKey = AES.parseKey(aesKey.getText());
        AES aes=new AES(aesKeyKey);
        String cipherText = aesCipherArea.getText();
        String plainText = aes.decrypt(Base64.decodeBase64(cipherText.getBytes()));
        aesPlainArea.setText(plainText);
    }

    private void aesEncrypt_Click(ActionEvent actionEvent) {
        Key aesKeyKey = AES.parseKey(aesKey.getText());
        AES aes=new AES(aesKeyKey);
        String plainText = aesPlainArea.getText();
        String cipherText = new String(Base64.encodeBase64(aes.encrypt(plainText)));
        aesCipherArea.setText(cipherText);
    }

    private void aesCreateKey_Click(ActionEvent actionEvent) {
        aesKey.setText(AES.createAESKey());
    }
}
