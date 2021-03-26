package via.sdj2.thread.external;

import via.sdj2.thread.view.TemperatureViewController;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class RunnableClock implements Runnable {
    private TemperatureViewController GUI;

    public RunnableClock(TemperatureViewController GUI) {
        this.GUI = GUI;
    }

    @Override
    public void run() {
        while(true) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(timeFormatter);
            System.out.println(timeString);
//            GUI.showTime(timeString);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
