package com.ifmo.lesson18;

import java.util.ArrayList;
import java.util.List;

public class AES {
    private int temperature;

    private List<Listener> listenersGreen = new ArrayList<>();
    private List<Listener> listenersYellow = new ArrayList<>();
    private List<Listener> listenersRed = new ArrayList<>();

    public void subscribeGreen(Listener listener) {
        listenersGreen.add(listener);
    }
    public void unsubscribeGreen(Listener listener)
    {
        listenersGreen.remove(listener);
    }

    public void subscribeYellow(Listener listener) {
        listenersYellow.add(listener);
    }
    public void unsubscribeYellow(Listener listener)
    {
        listenersYellow.remove(listener);
    }

    public void subscribeRed(Listener listener) {
        listenersRed.add(listener);
    }
    public void unsubscribeRed(Listener listener)
    {
        listenersRed.remove(listener);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        if (temperature >= 200){
            notifyListeners("RED: CRITICAL, temperature is " + temperature +" degrees", listenersRed);
        } else if (temperature >= 100) {
            notifyListeners("YELLOW: WARNING, temperature is " + temperature +" degrees", listenersYellow);
        } else if (temperature >= 50) {
            notifyListeners("GREEN: temperature is " + temperature +" degrees", listenersGreen);
        }
    }

    private void notifyListeners(String message, List<Listener> listeners) {
        for (Listener listener : listeners)
            listener.publish(message);
    }
}
