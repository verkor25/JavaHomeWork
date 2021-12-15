package com.pb.lozumirskij.hw11;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class PhoneBook extends ArrayList<PhoneAbonent> {

    public PhoneBook() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PhoneBook{}");
        sb.append("\n");
        for (PhoneAbonent pa : this) {
            sb.append(pa.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public int deleteByName (String name){
        int i = 0;
        int del = 0;
        while (i< super.size()) {
            if(name.equals(super.get(i).getFullName())) {
                super.remove(i);
                del++;
            }
            else {
                i++;
            }
        }
        return del;
    }

    public int searchByName(String name) {
        int search = -1;
        for (int i = 0; i< super.size(); i++){
            if(name.equals(super.get(i).getFullName())) {
                search = i;
            }
        }
        return search;
    }

    public void sortNameAndBorn() {
        Comparator<PhoneAbonent> comp = new PhoneAbonentCompareByFullName().thenComparing(new PhoneAbonentCompareByBorn());
        Collections.sort(this, comp);
    }

    public void change(int element, String fullName, LocalDate born, String address, List<PhoneNumber> phoneNumbers){
            super.get(element).setAddress(address);
            super.get(element).setBorn(born);
            super.get(element).setFullName(fullName);
            super.get(element).setPhoneNumbers(phoneNumbers);
            super.get(element).setModification(LocalDateTime.now());
    }

    public void createSerialTXT(String fileName){
        File file = Paths.get(Paths.get("").toAbsolutePath() + "\\" + fileName).toFile();

        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJSON(String fileName){
        File file = Paths.get(Paths.get("").toAbsolutePath() + "\\" + fileName).toFile();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        try (Writer writer = new FileWriter(file)){
            String json = mapper.writeValueAsString(this);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readSimpleJSON(String fileName){
        File file = Paths.get(Paths.get("").toAbsolutePath() + "\\" + fileName).toFile();
        String line;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            while((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result =  builder.toString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List abonents = mapper.readValue(result, List.class);
            System.out.println(abonents.getClass().getName());

            for(int i = 0; i < abonents.size(); i++) {
                System.out.println(abonents.get(i));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public PhoneBook readJSON(String fileName){

        PhoneBook book = new PhoneBook();
        File file = Paths.get(Paths.get("").toAbsolutePath() + "\\" + fileName).toFile();
        try {
            FileReader reader = new FileReader(file);

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray)  jsonParser.parse(reader);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String fullName = (String) jsonObject.get("fullName");
                JSONArray bornArr = (JSONArray) jsonObject.get("born");
                long yearBorn = (long) bornArr.get(0);
                long monthBorn = (long) bornArr.get(1);
                long dayBorn = (long) bornArr.get(2);
                LocalDate born = LocalDate.of((int) yearBorn, (int) monthBorn, (int) dayBorn);
                String address = (String) jsonObject.get("address");
                JSONArray modArr = (JSONArray) jsonObject.get("modification");
                long year = (long) modArr.get(0);
                long month = (long) modArr.get(1);
                long day = (long) modArr.get(2);
                long hour = (long) modArr.get(3);
                long minute = (long) modArr.get(4);
                long second = (long) modArr.get(5);
                long nanoOfSecond = (long) modArr.get(6);
                LocalDateTime modification = LocalDateTime.of((int) year, (int) month, (int) day,
                        (int) hour, (int) minute, (int) second, (int) nanoOfSecond);
                JSONArray numbers = (JSONArray) jsonObject.get("phoneNumbers");
                List<PhoneNumber> phoneNumbers = new ArrayList<>();

                for (int j = 0; j < numbers.size(); j++) {
                    JSONObject innerObj = (JSONObject) numbers.get(j);
                    //String number = (String) innerObj.get("number");
                    StringBuilder element = new StringBuilder((String) innerObj.get("number"));
                    phoneNumbers.add(new PhoneNumber(element.toString()));
                }
                book.add(new PhoneAbonent(fullName, born, phoneNumbers,
                        address, modification));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return book;
    }

}
