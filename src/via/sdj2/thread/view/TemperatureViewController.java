package via.sdj2.thread.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import via.sdj2.thread.external.ObservableClock;
import via.sdj2.thread.external.RunnableClock;
import via.sdj2.thread.model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static java.lang.Thread.sleep;

public class TemperatureViewController implements PropertyChangeListener
{
  @FXML private TextField textInput;

  @FXML private Label labelTimer;

  @FXML private Label labelOutput;

  private TemperatureModel model;
  private Region root;
  private ViewHandler viewHandler;

  public TemperatureViewController()
  {
  }

  public void init(ViewHandler viewHandler, TemperatureModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    ObservableClock clock = new ObservableClock();
    clock.addListener(this);
    Thread clockThread = new Thread(clock);
    clockThread.setDaemon(true);
    clockThread.start();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    showTime(evt.getNewValue().toString());
  }

  private void showTime(String timeString){
    Platform.runLater(() -> labelTimer.setText(timeString));
    try {
      sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void reset()
  {
    textInput.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void toCelsius()
  {
    try
    {
      double value = Double.parseDouble(textInput.getText());
      double result = model.toCelsius(value);
      labelOutput.setText(
          textInput.getText() + " fahrenheit = " + result + " celsius");
      reset();
    }
    catch (Exception e)
    {
      labelOutput.setText("Error in input");
    }
  }

  @FXML private void toFahrenheit()
  {
    try
    {
      double value = Double.parseDouble(textInput.getText());
      double result = model.toFahrenheit(value);
      labelOutput.setText(
          textInput.getText() + " celsius = " + result + " fahrenheit");
      reset();
    }
    catch (Exception e)
    {
      labelOutput.setText("Error in input");
    }
  }
}