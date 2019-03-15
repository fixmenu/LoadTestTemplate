package com.asimio.api.demo.model;

import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProfanityModel {

    private List<ProfanityText> textList;
    private int serviceId;

    public ProfanityModel(){
        textList = new ArrayList<>();
        serviceId = new Random().nextInt(9999);
    }

    public List<ProfanityText> getTextList() {
        return textList;
    }

    public void setTextList(List<ProfanityText> textList) {
        this.textList = textList;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void addText(ProfanityText text){
        textList.add(text);
    }

    @Override
    public String toString() {
        return "{" +
                "\"textList\":" + textList +
                ", \"serviceId\":" + serviceId +
                '}';
    }
}
