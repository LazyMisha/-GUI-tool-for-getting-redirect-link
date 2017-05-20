package application;

import window.MainWindow;

/**
 * Created by user on 5/19/2017.
 */
public class Application {
    public void run(){
        try {
            new MainWindow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
