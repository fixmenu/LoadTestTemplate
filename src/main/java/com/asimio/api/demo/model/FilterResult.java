package com.asimio.api.demo.model;

public class FilterResult {
    private String text;
    private int filterCode;

    public FilterResult() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFilterCode() {
        return filterCode;
    }

    public void setFilterCode(int filterCode) {
        this.filterCode = filterCode;
    }

    @Override
    public String toString() {
        return "FilterResult{" +
                "text='" + text + '\'' +
                ", filterCode=" + filterCode +
                '}';
    }
}
