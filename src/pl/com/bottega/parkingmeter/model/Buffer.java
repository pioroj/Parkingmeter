package pl.com.bottega.parkingmeter.model;


import pl.com.bottega.parkingmeter.model.Money;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Buffer {
    private Set<Money> acceptableMoney = new HashSet<>();
    private ArrayList<Money> temporaryBuffer = new ArrayList<>();

    public Buffer(Set<Money> acceptableMoney) {
        this.acceptableMoney = acceptableMoney;
    }

    public void checkValue(Money money) {
        if (!acceptableMoney.contains(money))
            throw new IllegalArgumentException("This value is not acceptable.");
    }

    public void acceptMoney(Money money) {
        checkValue(money);
        temporaryBuffer.add(money);
    }

    public void moveFromBufferToCartridge(Cartridge cartridge) {
        if (temporaryBuffer.isEmpty())
            throw new IllegalStateException("Buffer is empty.");

        for (Money money : temporaryBuffer) {
            cartridge.getMoney(money);
        }
        temporaryBuffer.clear();
    }

    public ArrayList<Money> giveInsertedMoneyBack() {
        ArrayList<Money> change = new ArrayList<>(temporaryBuffer);
        temporaryBuffer.clear();
        return change;
    }
}
