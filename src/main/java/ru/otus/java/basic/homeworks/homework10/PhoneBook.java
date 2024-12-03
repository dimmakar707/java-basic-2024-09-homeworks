package ru.otus.java.basic.homeworks.homework10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private Map<String, String> phoneList = new HashMap<String, String>();

    public Map<String, String> getPhoneList() {
        return phoneList;
    }

    public void add(String phone, String fio) {
        phoneList.put(phone, fio);
    }

    public ArrayList<String> find(String fio) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : phoneList.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(fio)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        return phoneList.containsKey(phoneNumber);
    }

}
