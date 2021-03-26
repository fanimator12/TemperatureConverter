package via.sdj2.thread.external;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class ObservableClock implements Runnable, PropertyChangeSubject {
    private PropertyChangeSupport property;

    public ObservableClock() {
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while(true) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(timeFormatter);
            System.out.println(timeString);
            new Thread(() -> property.firePropertyChange("time", "", timeString)).start();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
