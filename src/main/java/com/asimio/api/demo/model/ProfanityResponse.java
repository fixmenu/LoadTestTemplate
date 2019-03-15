package com.asimio.api.demo.model;

import java.util.List;

public class ProfanityResponse {
    private String txnid;
    private List<Bulk> bulkList;

    public ProfanityResponse(){

    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public List<Bulk> getBulkList() {
        return bulkList;
    }

    public void setBulkList(List<Bulk> bulkList) {
        this.bulkList = bulkList;
    }

    @Override
    public String toString() {
        return "ProfanityResponse{" +
                "txnid='" + txnid + '\'' +
                ", bulkList=" + bulkList +
                '}';
    }
}
