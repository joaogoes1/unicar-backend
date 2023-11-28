package com.unicar.util.log;

public class Logger {
    public static void log(String message) {
        System.out.println("[UNICAR][LOG]: " message);
    }

    public static void error(String message) {
        System.err.println("[UNICAR][ERRO]: " + message);
    }
}
