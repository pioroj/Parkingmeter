package pl.com.bottega.parkingmeter.model.devices;

import pl.com.bottega.parkingmeter.model.Money;
import pl.com.bottega.parkingmeter.model.Time;

import java.time.LocalDateTime;

/**
 * Created by LENOVO on 2016-12-05.
 */
public class UserOutputDevice implements UserOutputInterface {
    @Override
    public void display(Time desiredTime, LocalDateTime dueDateTime, Money cost) {
        System.out.println("Desired time: "+desiredTime+". Due Time: "+dueDateTime+". Cost: "+cost);
    }
}
