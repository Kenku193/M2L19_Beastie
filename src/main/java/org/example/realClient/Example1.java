package org.example.realClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Example1 {

    // ХОСТЫ
    static final String HOST_ONE = "google.com";
    static final String HOST_TWO = "javarush.com";

    // ПОРТ
    static final int PORT = 80;

    // ЗАПРОС
    static final String REQUEST = "GET / HTTP/1.0\n"; // google.com/

    public static void main(String[] args) {

        System.out.println("ВЕДЕМ ОБМЕН С СЕРВЕРОМ: " + HOST_ONE);

        try {
            // СОЗДАЕМ СОКЕТ
            Socket socket = new Socket(HOST_ONE, PORT);

            // ДЛЯ ОБМЕНА НУЖНЫ ВХОДНОЙ И ВЫХОДНОЙ ПОТОКИ
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ОТПРАВЛЯЕМ ЗАПРОС ПО ПРОТОКОЛУ HTTP ВЕРСИИ 1.0 ПО МЕТОДУ GET
            out.println(REQUEST);

            // ПОЛУЧАЕМ ОТВЕТ ПОСТРОЧНО
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
