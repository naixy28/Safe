package tabPanels;

import org.apache.commons.codec.binary.Base64;
import security.RSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.Key;
import java.security.KeyPair;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class RSAPanel extends JPanel{

    private final JTextArea rsaPrivateKey;
    private final JTextArea rsaPublicKey;
    private final JTextArea rsaPlainArea;
    private final JTextArea rsaCipherArea;

    public RSAPanel() {
        this.setLayout(new BorderLayout());
        JButton rsaCreateKey= new JButton("生成RSA密钥对");
        rsaCreateKey.addActionListener(this::rsaCreateKey_Click);
        this.add(rsaCreateKey,BorderLayout.NORTH);

        JPanel rsaCenterPanel = new JPanel(new FlowLayout());
        this.add(rsaCenterPanel, BorderLayout.CENTER);

        JLabel rsaLabel= new JLabel("私钥:");
        rsaCenterPanel.add(rsaLabel);
        rsaPrivateKey = new JTextArea(4,18);
        rsaPrivateKey.setLineWrap(true);
        rsaCenterPanel.add(rsaPrivateKey);
        JLabel rsaLabel2= new JLabel("公钥:");
        rsaCenterPanel.add(rsaLabel2);
        rsaPublicKey = new JTextArea(4,8);
        rsaPublicKey.setLineWrap(true);
        rsaCenterPanel.add(rsaPublicKey);
        JLabel rsaPlainLabel= new JLabel("请输入明文:");
        rsaCenterPanel.add(rsaPlainLabel);
        rsaPlainArea = new JTextArea(4,25);
        rsaPlainArea.setLineWrap(true);
        rsaCenterPanel.add(rsaPlainArea);
        JLabel rsaCipherLabel= new JLabel("请输入密文:");
        rsaCenterPanel.add(rsaCipherLabel);
        rsaCipherArea = new JTextArea(4,25);
        rsaCipherArea.setLineWrap(true);
        rsaCenterPanel.add(rsaCipherArea);
        JButton rsaEncrypt= new JButton("加密");
        rsaEncrypt.addActionListener(this::rsaEncrypt_Click);
        rsaCenterPanel.add(rsaEncrypt);
        JButton rsaDecrypt= new JButton("解密");
        rsaDecrypt.addActionListener(this::rsaDecrypt_Click);
        rsaCenterPanel.add(rsaDecrypt);
    }

    private void rsaDecrypt_Click(ActionEvent actionEvent) {
        byte[] privateKey = RSA.decode(rsaPrivateKey.getText());
        String cipherText = rsaCipherArea.getText();
        String plainText = RSA.decrypt(privateKey, Base64.decodeBase64(cipherText.getBytes()));
        rsaPlainArea.setText(plainText);
    }

    @SuppressWarnings("ConstantConditions")
    private void rsaEncrypt_Click(ActionEvent actionEvent) {
        byte[] publicKey = RSA.decode(rsaPublicKey.getText());
        String plainText = rsaPlainArea.getText();
        String cipherText = new String(Base64.encodeBase64(RSA.encrypt(publicKey, plainText)));
        rsaCipherArea.setText(cipherText);
    }

    private void rsaCreateKey_Click(ActionEvent actionEvent) {
        KeyPair kp = RSA.generateKP();
        rsaPrivateKey.setText(RSA.getPrivateKey(kp));
        rsaPublicKey.setText(RSA.getPublicKey(kp));
    }

}
