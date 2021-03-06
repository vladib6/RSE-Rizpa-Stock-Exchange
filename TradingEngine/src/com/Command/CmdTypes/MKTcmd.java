package com.Command.CmdTypes;

import com.Actions.Transaction;
import com.AlertDTO;
import com.User.Traderinterface;
import com.stock.Stock;

public class MKTcmd extends CommandType {
    public MKTcmd(Traderinterface user, Direction direction, String stockName, int numOfStocks) {
        super(user,direction, numOfStocks, stockName,0);

    }


    @Override
    public int Execute(Stock stock) {//return the number if transaction that made
        synchronized (stock) {
            int numOfTransactions = 0;
            Transaction newTransaction;
            do {
                newTransaction = Findcmd(stock);//try to find first matching opposite command and do transaction
                if (newTransaction != null) {
                    stock.addTransaction(newTransaction);
                    numOfTransactions++;
                    stock.setTransactionTurnover(newTransaction.getTurnover());
                }
            }
            while (newTransaction != null && numOfStocks != 0);//while has stocks in command and success do transaction

            if (numOfStocks > 0) {//if there is stocks in command so add the command to waiting list
                this.price = stock.getCurrentPrice();
                stock.addWaitingCommand(this);
            }
            return numOfTransactions;
        }
    }


    public Transaction FindSellcmd(Stock stock) {//find the matching sell command to buy command that execute
                if(stock.getSellWaitinglist().getSellwaitinglist().size()==0){//if the waiting command list is empty return null
                    return null;
                }else {
                    Transaction transaction=DoTransaction(this,stock.getSellWaitinglist().getFirst(),stock.getSellWaitinglist().getFirst().price,stock);
                    this.price=transaction.getPrice();//update the command price in case we pass the command to waiting list
                    if(stock.getSellWaitinglist().getFirst().numOfStocks==0){ //if numofstock is 0 so remove the cmd from waiting list
                        stock.getSellWaitinglist().getSellwaitinglist().remove(0);
                    }
                    return transaction;
                }
    }

    public Transaction FindBuycmd(Stock stock) {
        if(stock.getBuyWaitinglist().getBuywaitinglist().size()==0){//if the waiting command list is empty return null
            return null;
        }else {
            Transaction transaction=DoTransaction(stock.getBuyWaitinglist().getFirst(),this,stock.getBuyWaitinglist().getFirst().price,stock);
            this.price=transaction.getPrice();
            if(stock.getBuyWaitinglist().getFirst().numOfStocks==0){ //if numofstock is 0 so remove the cmd from waiting list
                stock.getBuyWaitinglist().getBuywaitinglist().remove(0);
            }
            return transaction;
        }
    }

    public Transaction Findcmd(Stock stock){
        if(super.direction==Direction.SELL) {
            return FindBuycmd(stock);
        }   else{
            return FindSellcmd(stock);
        }
    }

    }