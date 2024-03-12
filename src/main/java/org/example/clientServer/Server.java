package org.example.clientServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static int i = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(25000);

        int localPort = serverSocket.getLocalPort();
        System.out.println(localPort);

        System.out.println("Сервер запущен!");

        while (true){
            // С ЭТОЙ ТОЧКИ СЕРВЕР ОЖИДАЕТ ВХОДЯЩИЕ ЗАПРОСЫ
            Socket accept = serverSocket.accept();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

            String request = bufferedReader.readLine();
            System.out.println("Запрос получен! " + ++i);

            String response = "Привет! Я ответ!";

            bufferedWriter.write(response);
            bufferedWriter.flush();


            accept.close();
            bufferedWriter.close();
            bufferedReader.close();
        }

    }

}
