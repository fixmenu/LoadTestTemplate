package com.asimio.api.demo.model;

import java.util.List;

public class Bulk {
    private String serviceId;
    private List<FilterResult> filterResultList;

    public Bulk(){

    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<FilterResult> getFilterResultList() {
        return filterResultList;
    }

    public void setFilterResultList(List<FilterResult> filterResultList) {
        this.filterResultList = filterResultList;
    }

    @Override
    public String toString() {
        return "Bulk{" +
                "serviceId='" + serviceId + '\'' +
                ", filterResultList=" + filterResultList +
                '}';
    }
}
