package com.example.question1.Service;


import com.example.question1.Entity.Entry;
import com.example.question1.Repository.EntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    private EntryDAO entryDAO;
    @Override
    public String saveEntries(List<Entry> entries) {
        for(Entry i:entries) entryDAO.save(i);
        return "entry is saved";
    }
}
