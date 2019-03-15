package com.asimio.api.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfanityRequest {
    private String txnid;
    private List<ProfanityModel> bulkList;

    public ProfanityRequest(){
        this.txnid = UUID.randomUUID().toString();
        bulkList = new ArrayList<>();
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public List<ProfanityModel> getBulkList() {
        return bulkList;
    }

    public void setBulkList(List<ProfanityModel> bulkList) {
        this.bulkList = bulkList;
    }

    public void addProfanityModel(ProfanityModel profModel) {
        bulkList.add(profModel);
    }

    @Override
    public String toString() {
        return "{" +
                "\"txnid\":\"" + txnid + '\"' +
                ", \"bulkList\":" + bulkList +
                '}';
    }
}
