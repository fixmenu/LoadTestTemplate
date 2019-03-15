package com.asimio.api.demo.reader;

import com.asimio.api.demo.model.ProfanityModel;
import com.asimio.api.demo.model.ProfanityRequest;
import com.asimio.api.demo.model.ProfanityText;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Component
public class CsvReader {
    private static final String FILE_PATH = "config/profanity/webchat_intertionmessage_sample.csv";


    public static List<ProfanityRequest> getData() {
        List<ProfanityRequest> profanityRequestList = new ArrayList<>();
        ProfanityRequest profanityRequest = new ProfanityRequest();
        ProfanityModel profanityModel = new ProfanityModel();
        Random random = new Random();
        int count = 1;
        try {
            FileReader reader = new FileReader(FILE_PATH);
            CSVReader csvReader = new CSVReader(reader,'|');
            String[] nextRecord;
            while((nextRecord =csvReader.readNext()) != null){
                if(nextRecord.length < 3){
                    continue;
                }

                if(count % 5 == 0){
                    profanityRequest.addProfanityModel(profanityModel);
                    profanityModel = new ProfanityModel();
                }

                profanityModel.addText(new ProfanityText(nextRecord[2]));

                if(count == 101){
                    profanityRequestList.add(profanityRequest);
                    profanityRequest = new ProfanityRequest();
                    count = 1;
                }

                count++;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return profanityRequestList;
    }

}
