package com.ram.apiCreation.controller;


import com.ram.apiCreation.Service.JournalEntryService;
import com.ram.apiCreation.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Journals")
public class journalController {
    @Autowired
    private JournalEntryService journalEntryService;


    @PostMapping("/saveEntry/{userId}")
    public ResponseEntity<?> saveEntry(@RequestBody JournalEntry journalEntry, @PathVariable ObjectId userId){
        if(journalEntryService.saveEntry(journalEntry, userId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<JournalEntry>> getEntry(@PathVariable ObjectId userId){
        List<JournalEntry> allJournals = journalEntryService.getEntries(userId);
        if(allJournals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allJournals, HttpStatus.OK);
    }


}
