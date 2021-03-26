package via.sdj2.thread.external;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    public void addListener(PropertyChangeListener listener);
    public void removeListener(PropertyChangeListener listener);
}
