package test;


import soft.Client;
import soft.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("ソケット通信");
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JButton cButton = new JButton("クライアントモード");
        JButton sButton = new JButton("サーバモード");

        cButton.addActionListener((e) ->{

            new Thread(()->{
                System.out.println(e.getActionCommand());
                Client client = new Client();
                client.run();
            }).start();});

        //ややこしく書いた ActionListernerをラムダで書いてその中の処理にスレッドを使用するが、それもラムダで書いた。一見意味不明
        sButton.addActionListener((e) ->{
            new Thread(()->{
                System.out.println(e.getActionCommand());
                Server server = new Server();
                //server.run();
            }).start();
        });


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(cButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(sButton, gbc);
        frame.setVisible(true);
    }


}
