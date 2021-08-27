package soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.SocketException;

public class Frame extends JFrame implements ActionListener {

    JButton runButton = new JButton("run");
    JTextField ipField = new JTextField("192.168.11.2", 10);
    JTextField portField = new JTextField("8765", 10);
    JTextArea textArea = new JTextArea(10, 10);

    Frame(String title, Dimension size) {
        super(title);
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();

        ipField.setToolTipText("ip");
        portField.setToolTipText("port");

        gbc.gridx = 1;
        gbc.gridy = 0;
        layout.setConstraints(ipField, gbc);
        this.add(ipField);

        gbc.gridx = 1;
        gbc.gridy = 1;
        layout.setConstraints(portField, gbc);
        this.add(portField);

        gbc.gridx = 1;
        gbc.gridy = 2;
        layout.setConstraints(runButton, gbc);
        this.add(runButton);


        JTextAreaStream jTextAreaStream = new JTextAreaStream(textArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        layout.setConstraints(textArea, gbc);
        this.add(textArea);
        System.setOut(new PrintStream(jTextAreaStream, true));


        runButton.addActionListener(this);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(() -> {
            while (true) {

                Server server = null;
                try {
                    server = new Server();

                    //https://www.geekpage.jp/programming/winsock/so_reuseaddr.php

                    server.run(ipField.getText(), Integer.valueOf(portField.getText()));

                    server.allClose();
                } catch (Exception exc) {
                    server.allClose();
                    exc.printStackTrace();
                    System.out.println("zzz");
                }


            }
        }).start();

    }
}
