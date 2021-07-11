package com.User;

import com.Actions.ActionEntry;
import com.HoldingsDTO;
import com.UserDTO;
import com.stock.Stock;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Trader implements Traderinterface , User{

   public Trader(String username){
       actionsHistory=new LinkedList<>();
       holdings=new Holdings();
       this.username=username;
       this.accountBalance=0;
       this.numOfTransactions=0;
   }

    String username;
    Holdings holdings;
    LinkedList<ActionEntry> actionsHistory;
    int accountBalance;
    int numOfTransactions;

    public void setActionsHistory(ActionEntry actionEntry){
        actionsHistory.addFirst(actionEntry);
        actionEntry.setOldSum(accountBalance);
        actionEntry.setNewSum(accountBalance+actionEntry.getAction().getTurnover());
        this.accountBalance=actionEntry.getNewSum();
    }
    public LinkedList<ActionEntry> getActionsHistory() { return actionsHistory; }

    @Override
    public int CalcCurrentHoldings(){ return  holdings.currentHoldings(); }

    @Override
    public void addHoldings(Stock stock, int quantity) {
        holdings.addToHoldings(stock, quantity);
    }

    @Override
    public void removeHoldings(Stock stock, int quantity) {
        holdings.removeFromHoldings(stock, quantity);
    }

    @Override
    public void chargeMoney(int num) {
        this.accountBalance+=num;
    }

    public UserDTO createDTO(){
        return  new UserDTO(CalcCurrentHoldings(),username, accountBalance,numOfTransactions);
    }
    public List<HoldingsDTO> getHoldingsDTO(){ return holdings.createDTO(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trader trader = (Trader) o;
        return Objects.equals(username, trader.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getUserType() {
        return Trader.class.getSimpleName();
    }
}
