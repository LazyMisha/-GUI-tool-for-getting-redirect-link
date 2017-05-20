package window;

import service.CheckLink;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 5/19/2017.
 */
public class MainWindow {

    JFrame mainWindow;
    JPanel jPanel;
    JTextField jTextField;
    JLabel jLabelForTextField;
    JLabel jLabelForResults;
    JLabel jLabelIcon;
    JButton jButton;
    GridBagConstraints gbc;
    Image button;
    Image icon;

    CheckLink checkLink = new CheckLink();
    String URL = "";

    public MainWindow() throws Exception{
        mainWindow = new JFrame("Tool for getting redirect link");
        mainWindow.setPreferredSize(new Dimension(450, 280));
        jPanel = new JPanel();
        button = ImageIO.read(getClass().getResourceAsStream("/send.png"));
        icon = ImageIO.read(getClass().getResourceAsStream("/icon.png"));

        jTextField = new JTextField(20);
        jTextField.setBackground(Color.LIGHT_GRAY);
        jLabelForTextField = new JLabel("Type URL:");
        jButton = new JButton(new ImageIcon(button));
        jLabelForResults = new JLabel(URL);
        jLabelIcon = new JLabel(new ImageIcon(icon));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        jPanel.add(jLabelForTextField, gbc);
        gbc.gridy++;
        jPanel.add(jTextField, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        jPanel.add(jButton, gbc);
        gbc.gridy++;
        jPanel.add(jLabelForResults, gbc);
        gbc.gridx++;
        gbc.gridy = 0;
        jPanel.add(jLabelIcon, gbc);

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jButton) {
                    try {
                        URL = checkLink.printredirect(jTextField.getText());
                        System.out.println(URL);
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                        URL = e1.getMessage();
                    }
                    int input = JOptionPane.showOptionDialog(mainWindow, URL, "Redirect Link",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Copy", "Close"}, "default");
                    UIManager.put("OptionPane.cancelButtonText", "Close");
                    UIManager.put("OptionPane.okButtonText", "Copy");
                    if(input == JOptionPane.OK_OPTION) {
                        StringSelection stringSelection = new StringSelection(URL);
                        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clpbrd.setContents(stringSelection, null);
                    }
                }
            }
        });

        mainWindow.getContentPane().add(jPanel, BorderLayout.CENTER);

        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);

        mainWindow.pack();
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}