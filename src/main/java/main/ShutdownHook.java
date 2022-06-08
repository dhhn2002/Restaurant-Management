package main;

import controllers.TimeCouterController;

public class ShutdownHook extends Thread {

    @Override
    public void run() {
        //Khi đóng đột ngột
        System.out.println("Chuong trinh dừng đột ngột!");
        try {
            TimeCouterController.stop();
            if (SessionManager.getSession() != null) {
                System.out.println("Kết thúc phiên làm việc!");
                SessionManager.update();// Kết thúc phiên làm việc
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
