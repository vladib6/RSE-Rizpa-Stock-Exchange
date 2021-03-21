package com.Engine;

import com.Command.CmdTypes.CommandType;
import com.StockDTO;
import com.TransactionDTO;
import com.User.Userinterface;
import com.stock.Allstocks;
import com.stock.Stock;
import com.stock.StockArray;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@XmlRootElement(name="rizpa-stock-exchange-descriptor")
public class MainEngine implements EngineInterface {

    public MainEngine(){
        allStocks= new Allstocks();
        stockArray=new StockArray();
    }
   private Allstocks allStocks;
   private StockArray stockArray;
   private Userinterface connectedUser;

    @XmlElement(name="rse-stocks")
    public StockArray getStockArray() {
        return stockArray;
    }

    public void setStockArray(StockArray stockArray) {
        this.stockArray = stockArray;
    }


    public Allstocks getAllStocks() {
        return allStocks;
    }


    public void addStock(Stock stock) throws StockException {
        if(allStocks.getAllStocks().containsKey(stock.getSymbol())){
            throw new StockException(stock.getSymbol(),"Symbol");
        }
        if(allStocks.isCompanyNameExist(stock.getCompanyName())){
            throw new StockException(stock.getCompanyName(),"Company name");
        }else{
            allStocks.addStock(stock);
        }

    }


    public Stock getStockByName(String symbol){
       return allStocks.getStockByName(symbol);
    }

    public StockDTO getStockDto(String symbol){
            Stock tempStock=allStocks.getStockByName(symbol);
            return tempStock.createStockDto();
    }

    public List<StockDTO> getAllstocksDto(){
        List<StockDTO> res=new ArrayList<>();
        for(Map.Entry<String,Stock> entry: allStocks.getAllStocks().entrySet()){
            res.add(entry.getValue().createStockDto());
        }

        return res;
    }
    public void Connect(Userinterface user){
        connectedUser=user;
    }

    public Userinterface getConnectedUser() { return connectedUser; }

    public boolean isStockExist(String symbol){
        boolean res=false;
        for(Map.Entry<String,Stock> entry:getAllStocks().getAllStocks().entrySet()){
            if(entry.getKey()==symbol){
                res=true;
                return  res;
            }
        }
        return res;
    }

    public LinkedList<TransactionDTO> getTransactionListDtoByStock(String symbol){
       return getStockByName(symbol).createTransactionDTOlist();
    }

    public int ExecuteCmd(CommandType cmd){
       return cmd.Execute(getStockByName(cmd.getStockSymbol()));
    }
}
