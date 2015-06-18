import security.AES;
import security.DES;
import security.RSA;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.codec.binary.Base64;
import tabPanels.AESPanel;
import tabPanels.DESPanel;
import tabPanels.MD5Panel;
import tabPanels.RSAPanel;

/**
 * Created by 电脑 on 2015/6/17.
 */
public class MainFrame extends JFrame {


    public MainFrame(){
        this.setSize(420, 280);
        this.setTitle("Encrypt Test Software ver 1.0.0.27");
        setLayout(new BorderLayout());
        JTabbedPane jTabbedPane = new JTabbedPane();
        this.add(jTabbedPane,BorderLayout.CENTER);
        JPanel aesPanel = new AESPanel();
        jTabbedPane.add("AES",aesPanel);
        JPanel desPanel = new DESPanel();
        jTabbedPane.add("DES",desPanel);
        JPanel rsaPanel = new RSAPanel();
        jTabbedPane.add("RSA",rsaPanel);
        JPanel md5Panel = new MD5Panel();
        jTabbedPane.add("MD5",md5Panel);
        jTabbedPane.addChangeListener(this::jTabbedPane_onTabChanged);


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void jTabbedPane_onTabChanged(ChangeEvent changeEvent) {
        JTabbedPane jTabbedPane = (JTabbedPane)changeEvent.getSource();
        int selectIndex = jTabbedPane.getSelectedIndex();
        switch (selectIndex){
            case 2:
                this.setSize(420, 580);
                break;
            case 3:
                this.setSize(420,130);
                break;
            case 0:
            case 1:
            default:
                this.setSize(420, 280);
                break;
        }
    }


}
