package com.ram.apiCreation.controller;


import com.ram.apiCreation.Service.JournalEntryService;
import com.ram.apiCreation.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

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
    public boolean pushData(@RequestBody JournalEntry entry1){
        journalEntryService.saveEntry(entry1);
        return true;
    }

    @GetMapping("/get/{myID}")
    public Optional<JournalEntry> titleByID(@PathVariable String myID){

        return journalEntryService.getEntrybyId(myID);
    }

    @DeleteMapping("/del/{myID}")

    public boolean delID(@PathVariable long myID){
        return true;
    }


}
