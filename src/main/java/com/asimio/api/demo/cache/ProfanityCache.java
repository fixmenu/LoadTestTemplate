package com.asimio.api.demo.cache;

import com.asimio.api.demo.model.ProfanityRequest;
import com.asimio.api.demo.reader.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfanityCache {
    @Autowired
    CsvReader csvReader;

    List<ProfanityRequest> profanityRequestList;

    public ProfanityCache(){
        this.profanityRequestList = csvReader.getData();
    }

    public List<ProfanityRequest> getProfanityRequest(){
        return profanityRequestList;
    }
}
