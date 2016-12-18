package pl.com.bottega.parkingmeter.application;


import pl.com.bottega.parkingmeter.model.*;
import pl.com.bottega.parkingmeter.model.devices.TicketPrinter;
import pl.com.bottega.parkingmeter.model.devices.UserOutputDevice;
import pl.com.bottega.parkingmeter.model.devices.UserOutputInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TestApplication {
    public static void main(String[] args) {

        //Stworzenie kasetki z pieniędzmi i bufora
        Map<Money, Integer> testCartridge = new HashMap<>();

        Money jedenZloty = new Money("1", "PLN");
        testCartridge.put(jedenZloty, 30);

        Money dwaZlote = new Money("2", "PLN");
        testCartridge.put(dwaZlote, 30);

        Money piecZlotych = new Money("5", "PLN");
        testCartridge.put(piecZlotych, 30);

        Money dziesiecGroszy = new Money("0.1", "PLN");
        testCartridge.put(dziesiecGroszy, 30);

        Money dwadziesciaGroszy = new Money("0.2", "PLN");
        testCartridge.put(dwadziesciaGroszy, 30);

        Money piecdziesiatGroszy = new Money("0.5", "PLN");
        testCartridge.put(piecdziesiatGroszy, 30);



        Parkingmeter parkingmeter = new Parkingmeter(new UserOutputInterface() {
            @Override
            public void display(Time desiredTime, LocalDateTime dueDateTime, Money cost) {
                System.out.println("desiredTime = [" + desiredTime + "], dueDateTime = [" + dueDateTime + "], cost = [" + cost + "]");
            }
        }, new TicketPrinter(), new Cartridge(testCartridge), new Buffer(testCartridge.keySet()));

        //symulacja zachowania usera
        parkingmeter.add(new Time(2, Time.TimeUnit.MIN));

        parkingmeter.topUp(dwaZlote);

        System.out.println("=========");

        parkingmeter.confirm();


    }
}
