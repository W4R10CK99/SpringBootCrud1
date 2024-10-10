package com.ram.apiCreation.controller;


import com.ram.apiCreation.Repository.JournalEntryRepository;
import com.ram.apiCreation.Service.JournalEntryService;
import com.ram.apiCreation.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class controller_V2 {

    @Autowired
    private JournalEntryService journalEntryService;



    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getEntries();
    }



    @PostMapping
    public ResponseEntity<?> pushData(@RequestBody JournalEntry entry1){
        entry1.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry1);
        return new ResponseEntity<JournalEntry>(entry1, HttpStatus.OK);

    }

    @GetMapping("/get/{myID}")
    public ResponseEntity<?> titleByID(@PathVariable ObjectId myID){

        Optional<JournalEntry> journalEntry = journalEntryService.getEntrybyId(myID);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        return journalEntryService.getEntrybyId(myID).orElse(null);
    }

    @DeleteMapping("/del/{myID}")

    public boolean delID(@PathVariable ObjectId myID){
        journalEntryService.deleteById(myID);
        return true;
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry entry){

        JournalEntry old = journalEntryService.getEntrybyId(id).orElse(null);

        if(old!=null){
            old.setTitle(entry.getTitle()!=null && !entry.getTitle().isEmpty() ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!=null && !entry.getContent().isEmpty() ? entry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);

            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }





    }


}
