package com.Engine;

import com.Command.CmdTypes.CommandType;
import com.StockDTO;
import com.TransactionDTO;
import com.User.Userinterface;
import com.stock.Stock;

import java.util.LinkedList;
import java.util.List;

public interface EngineInterface {
    StockDTO getStockDto(String symbol);
    List<StockDTO> getAllstocksDto();
    Userinterface getConnectedUser();
    boolean isStockExist(String symbol);
    Stock getStockByName(String symbol);
    int ExecuteCmd(CommandType cmd);
    LinkedList<TransactionDTO> getTransactionListDtoByStock(String symbol);
    }
