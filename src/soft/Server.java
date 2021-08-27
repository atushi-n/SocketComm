package soft;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    ServerSocket sSocket;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public void run(String ip, int port) {


        sSocket = null;
        socket = null;
        reader = null;
        writer = null;
        String[] cList = {"calc", "notepad"};

        try{
            //IPアドレスとポート番号を指定してサーバー側のソケットを作成
            sSocket = new ServerSocket();

            sSocket.bind(new InetSocketAddress
                    (ip,port));

            System.out.println("クライアントからの入力待ち状態");

            //クライアントからの要求を待ち続けます
            socket = sSocket.accept();
            //クライアントからの受取用
            reader = new BufferedReader(
                    new InputStreamReader
                            (socket.getInputStream()));

            //サーバーからクライアントへの送信用
            writer = new PrintWriter(
                    socket.getOutputStream(), true);

            //無限ループ　byeの入力でループを抜ける
            String line = null;
            int num;

            Thread.sleep(500);//入力されたら少しスリープ
            writer.println("接続してくれてありがとう！");
            while (true) {

                line = reader.readLine();//相手からの入力待ち





                //writerに書き込みすることでレスポンス





                //こちら側で受け付けられるコマンドであった場合
                if(Arrays.asList(cList).contains(line)){
                    ProcessBuilder pb = new ProcessBuilder(line);
                    pb.start();
                }

                try{
                    num = Integer.parseInt(line);

                    if(num%2==0){
                        //送信用の文字を送信
                        writer.println("OK");
                    }else{
                        //送信用の文字を送信
                        writer.println("NG");
                    }
                }catch(NumberFormatException e){
                    //送信用の文字を送信
                    writer.println("数値を入力して下さい");
                }

                System.out.println("クライアントで入力された文字＝" + line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (reader!=null){
                    reader.close();
                }
                if (writer!=null){
                    writer.close();
                }
                if (socket!=null){
                    socket.close();
                }
                if (sSocket!=null){
                    sSocket.close();
                }
                System.out.println("サーバー側終了です");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void allClose(){
        try {
            sSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }

}

class STest{
    public static void main(String[] args) {
        Frame frame = new Frame("ソケット通信サーバ", new Dimension(500, 500));


    }
}

