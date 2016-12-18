package pl.com.bottega.parkingmeter.model.devices;

import pl.com.bottega.parkingmeter.model.Money;

import java.time.LocalDateTime;


public interface TicketPrinterInterface {
    public void printTicket(LocalDateTime maxDateTime, Money price);
}
