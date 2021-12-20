package com.pb.lozumirskij.hw12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook extends ArrayList<PhoneCustomer> {


    public PhoneBook() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PhoneBook");
        sb.append("\n");
        for (PhoneCustomer pa : this) {
            sb.append(pa.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public int deleteByName (String name){
        int del = 0;
        ArrayList<Integer> search = this.searchByName(name);
        if(search.get(0) != -1)
            for (Integer i: search) {
                this.remove((int) i);
                del++;
            }
        return del;
    }

    public ArrayList<Integer> searchByName(String name) {
        ArrayList<Integer> res = new ArrayList<>();
        int counter = 0;
        for (PhoneCustomer customer: this) {
            if(name.equals(customer.getFullName())) {
                res.add(counter);
            }
            counter++;
        }
        if(res.size() == 0) res.add(-1);
        return res;
    }

    public void sortNameAndBorn() {
        List list = this.stream()
                .sorted(Comparator.comparing(PhoneCustomer::getFullName).thenComparing((o1, o2) -> o1.getBorn().compareTo(o2.getBorn())))
                .collect(Collectors.toList());
        this.clear();
        this.addAll(list);
    }

    public void createJSON(String fileName){
        File file = Paths.get(Paths.get("").toAbsolutePath() + "\\" + fileName).toFile();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try (Writer writer = new FileWriter(file)){
            String json = mapper.writeValueAsString(this);
            writer.write(json);
        } catch (IOException e) {
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
                    StringBuilder element = new StringBuilder((String) innerObj.get("number"));
                    phoneNumbers.add(new PhoneNumber(element.toString()));
                }

                book.add(new PhoneCustomer(fullName, born, phoneNumbers,
                        address, modification));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return book;
    }

}
