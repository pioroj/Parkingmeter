package pl.com.bottega.parkingmeter.model;


import java.util.HashMap;
import java.util.Map;

public class Cartridge {
    protected Map<Money, Integer> moneyCartridge = new HashMap<>();
    protected Money totalMoney = Money.ZERO;

    public Cartridge(Map<Money, Integer> moneyCartridge) {
        this.moneyCartridge = moneyCartridge;
    }

    public void checkValue(Money money) {
        if (!moneyCartridge.containsKey(money))
            throw new IllegalArgumentException("Cartridge can not hold such money.");
    }

    public void getMoney(Money money) {
        checkValue(money);
        moneyCartridge.put(money, moneyCartridge.get(money) + 1);
        calculateTotalMoney();
    }

    private void calculateTotalMoney() {
        for (Money money : moneyCartridge.keySet())
            totalMoney = totalMoney.add(money.multiply(moneyCartridge.get(money)));
    }


}
