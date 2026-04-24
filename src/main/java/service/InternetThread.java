package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternetThread extends Thread {
    public InternetThread() {
        this.setDaemon(true);
    }
   public void run(){
       DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
       while (true) {
           try {
               Thread.sleep(300000);
               String currentTime = LocalDateTime.now().format(timeFormat);
               System.out.println("    ");
               System.out.println("Current time: " + currentTime);
               System.out.println("===================");
               System.out.println("|    Loading...   |");
               System.out.println("===================");
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               break;
           }
       }

    }

}
