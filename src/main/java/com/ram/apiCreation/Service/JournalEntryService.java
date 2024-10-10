package com.ram.apiCreation.Service;

import com.ram.apiCreation.Repository.JournalEntryRepository;
import com.ram.apiCreation.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntrybyId(ObjectId ID){
        return journalEntryRepository.findById(ID);

    }



    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);

    }


}
