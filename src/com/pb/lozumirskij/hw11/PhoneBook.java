package com.pb.lozumirskij.hw11;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.lang.reflect.Type;
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

    public void readJSON(String fileName){
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

            for(int i = 0; i < abonents.size(); i++) {
                System.out.println(abonents.get(i));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void readFullJSON(String fileName){
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
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);

        try {
            List<PhoneAbonent> abonents = mapper.readValue(result, new TypeReference<List<PhoneAbonent>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }

                @Override
                public int compareTo(TypeReference<List<PhoneAbonent>> o) {
                    return super.compareTo(o);
                }

            });
            for(int i = 0; i < abonents.size(); i++) {
                System.out.println(abonents.get(i));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
