package com.ram.apiCreation.Service;

import com.ram.apiCreation.Repository.JournalEntryRepository;
import com.ram.apiCreation.entity.JournalEntry;
import com.ram.apiCreation.entity.User;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    public boolean saveEntry(JournalEntry journalEntry, ObjectId userId){
        journalEntryRepository.save(journalEntry);

        Optional<User> userOptional = userService.getUserbyId(userId);
        if(userOptional.isEmpty()){
            return false;
        }
        User user = userOptional.get();
        user.getJournalEntries().add(journalEntry);
        userService.saveUser(user);
        return true;

    }

    public List<JournalEntry> getEntries(ObjectId userId){
        Optional<User> userOptional = userService.getUserbyId(userId);
        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> getEntrybyId(ObjectId ID){
        return journalEntryRepository.findById(ID);

    }



    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);

    }


}
