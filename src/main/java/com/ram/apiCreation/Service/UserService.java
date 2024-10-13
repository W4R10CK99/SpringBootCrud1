package com.ram.apiCreation.Service;


import com.ram.apiCreation.Repository.JournalEntryRepository;
import com.ram.apiCreation.Repository.UserRepo;
import com.ram.apiCreation.entity.JournalEntry;
import com.ram.apiCreation.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public boolean saveUser(User user){
        if(user != null){
            userRepo.save(user);
            return true;
        }
        else{
            return false;
        }
    }

    public Optional<User> getUserbyId(ObjectId id){
        return userRepo.findById(id);
    }

    public boolean delUserByID(ObjectId id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()){
            return false;
        }

        User user = userOptional.get();
        List<JournalEntry> journals = user.getJournalEntries();
        for(JournalEntry entry : journals){
            journalEntryRepository.deleteById(entry.getId());
        }

        userRepo.deleteById(id);

        return true;

    }




}
