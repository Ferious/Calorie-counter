package main;

import java.io.IOException;

/**
 * @author - team annonymous
 * @class - run thread for application
 */

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome into Calorie-Counter application below you can see the main menu !");
        new MainMenu().run();
        System.out.println("Goodbye see you soon !");
    }
}
