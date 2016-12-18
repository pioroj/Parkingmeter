package pl.com.bottega.parkingmeter.model.devices;

import pl.com.bottega.parkingmeter.model.Money;

import java.time.LocalDateTime;



public class TicketPrinter implements TicketPrinterInterface {
    @Override
    public void printTicket(LocalDateTime maxDateTime, Money price) {
        System.out.println("Your ticket is valid to: "+maxDateTime+". Cost: "+price);
    }
}
