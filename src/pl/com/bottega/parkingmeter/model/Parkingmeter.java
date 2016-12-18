package pl.com.bottega.parkingmeter.model;


import pl.com.bottega.parkingmeter.model.devices.TicketPrinter;
import pl.com.bottega.parkingmeter.model.devices.UserOutputInterface;

import java.time.LocalDateTime;
import java.util.*;


public class Parkingmeter {
    //urządzenia współpracujące
    private UserOutputInterface userOutputInterface;
    private TicketPrinter ticketPrinter;

    private Time userTime = Time.ZERO;
    private Money totalCost = Money.ZERO;
    private LocalDateTime maxDateTime = LocalDateTime.now();
    private Cartridge cartridge;
    private Buffer buffer;
    private Money balance = Money.ZERO;
    private PriceList priceList = new PriceList();

    public Parkingmeter(UserOutputInterface userOutputInterface, TicketPrinter ticketPrinter, Cartridge cartridge, Buffer buffer) {
        resetMachineState();
        this.userOutputInterface = userOutputInterface;
        this.ticketPrinter = ticketPrinter;
        this.cartridge = cartridge;
        this.buffer = buffer;
    }

    public void add(Time time) {
        userTime = userTime.add(time);
        totalCost = recalculateTotalCost();
        maxDateTime = recalculateMaxDateTime();
        userOutputInterface.display(userTime, maxDateTime, totalCost);
    }

    public void topUp(Money money) {
        //sprawdzic czy wartosc jest do przyjęcia
        cartridge.checkValue(money);
        //zwiększyć ilość wpłaconych pieniędzy
        balance = balance.add(money);
        //pieniądze przekazać do "bufora" aby móc je oddać w razie cancel lub darmowego parkowania gdyby nie było jak wydać reszty
        buffer.acceptMoney(money);
    }

    public void confirm() {
        //jeżeli ilosc wpłaconyc pieniedzy wystarczy na opłacenie totalCost - do Money dodać metody greaterThan(Money) greaterEquals(Money) i lessThan LessEquals aby móc porównać 2 wartosci money
        if (totalCost.greaterThan(balance)){
            System.out.println("Not enough money, missing: "+totalCost.substract(balance).toString());
            return;
        }
        //przeniesc pieniadze z bufora do kasetki
        buffer.moveFromBufferToCartridge(cartridge);
        //jeżeli należy się reszta do wydania
        //jeżeli są środki aby wydać restę to wydać jak nie to oddaćPieniądze()
        //wydać resztę
        if (totalCost.lessThan(balance)) {
            if (cartridge.totalMoney.greaterEquals(totalCost)){
                giveChange();
            } else {
                buffer.giveInsertedMoneyBack();
            }
        }
        //wydrukować bilet
        ticketPrinter.printTicket(maxDateTime, totalCost);


        //ResetStanuMaszyny() - warto zrobić metodę prywatnę, która resetuje i ją wywołać w konstruktorze również
        resetMachineState();
    }

    public void cancel() {
        //oddaćPieniądze()
        System.out.println("Take the money back.");
        buffer.giveInsertedMoneyBack();
        //ResetStanuMaszyny()
        resetMachineState();
    }

    private ArrayList<Money> giveChange() {
        Money amountOfChange = balance.substract(totalCost);
        ArrayList<Money> change = new ArrayList<>();
        Set<Money> keys = cartridge.moneyCartridge.keySet();
        List<Money> keysList = new ArrayList<Money>(keys);

        Collections.sort(keysList);

        for (Money money : keysList) {
            while (amountOfChange.substract(money).greaterEquals(Money.ZERO)) {
                amountOfChange = amountOfChange.substract(money);
                change.add(money);
            }
        }

        for (Money money: change) {
            System.out.println(money);
        }

        return change;
    }

    private LocalDateTime recalculateMaxDateTime() {
        return LocalDateTime.now().plusMinutes(userTime.getInMinutes());
    }

    private Money recalculateTotalCost() {
        //trzeba spawdzic ile mamy minut w userTime.getInMinutes() aby wiedzieć ile trzeba zapłacić za stawkę za określony czas
        //stawki mogą w cenniku być różne w zależności jak długo parkujemy...
        //gdyby ktoś miał problem z tym algorytmem to niech założy, że stawka jest stała - jedna pozycja w cenniku
        return priceList.getParkingCost(userTime);
    }

    private void resetMachineState(){
        userTime = Time.ZERO;
        totalCost = Money.ZERO;
        maxDateTime = LocalDateTime.now();
    }

}
