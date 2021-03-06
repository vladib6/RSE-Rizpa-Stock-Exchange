import { useEffect, useState } from "react";
import { useParams } from "react-router";
import api from "../api/api";
import { useGlobalContext } from "../App";

interface stockData{
    symbol:string,
    companyName:string,
    currentPrice:number,
    TransactionTurnover:number
}

interface RouteProps {
    stockname:string,
    name:string
}

export function Stockdata (){
    const {username,type}=useGlobalContext()
    const {stockname,name}=useParams<RouteProps>()
    const [data,setData]=useState<stockData>()
    const [stockHolding,setStockHolding]=useState(0) 
    
    useEffect(()=>{
        const interval1=setInterval(async()=>{
            await api.get('/api/stock?stock='+stockname)
            .then(res=>setData(res.data) )
            .catch(err=>console.log(err))
        },5000);
       
            const interval2=setInterval(async()=>{
                if(type!=="Admin"){
                    await api.get('/api/stockholding?stock='+stockname+'&user='+name)
                    .then(res=>setStockHolding(res.data as number) )
                    .catch(err=>console.log(err))
                }
            },5000); 
        return ()=>{clearInterval(interval1)
            clearInterval(interval2)};
    },[])


    return (
        <div className="row">
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-primary fw-bold text-xs mb-1"><span>Stock Name</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.symbol}</span></div>
                                </div>
                                <div className="col-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-success py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-success fw-bold text-xs mb-1"><span>Comapany Name</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span></span>{data?.companyName}</div>
                                </div>
                                <div className="col-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
               
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-warning py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-warning fw-bold text-xs mb-1"><span>Current Rate</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.currentPrice} $</span></div>
                                </div>
                                <div className="col-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-success py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-success fw-bold text-xs mb-1"><span>Turnover</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.TransactionTurnover} $</span></div>
                                </div>
                                <div className="col-auto"><i className="fas fa-dollar-sign fa-2x text-gray-300"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
                {type==="Trader"?<div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-success py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-success fw-bold text-xs mb-1"><span>The amount of stocks in your holdings</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{stockHolding} </span></div>
                                </div>
                                <div className="col-auto"><i className="fas fa-dollar-sign fa-2x text-gray-300"></i></div>
                            </div>
                        </div>
                    </div>
                </div>:null}
            </div>   
    )
}