package com.journal.japp.controller;


import com.journal.japp.entity.JournalEntry;
import com.journal.japp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


  //  private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }


    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDateTime(LocalDateTime.now());
        journalEntryService.saveJournal(journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getJournalEntrybyId(@PathVariable ObjectId myId){
        return journalEntryService.getbyId(myId);
    }

    @DeleteMapping("id/{myId}")
    public void deleteJournalEntrybyId(@PathVariable ObjectId myId){
         journalEntryService.deletebyId(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntrybyId(@PathVariable ObjectId myId,@RequestBody JournalEntry journalEntry){

        JournalEntry old = journalEntryService.getbyId(myId).orElse(null);

        if(old !=null){
            old.setName(journalEntry.getName() !=null && !journalEntry.getName().equals("") ? journalEntry.getName() : old.getName());
            old.setDescription(journalEntry.getDescription() !=null && !journalEntry.getDescription().equals("") ? journalEntry.getDescription() : old.getDescription());
        }
        journalEntryService.updatebyId(old);
        return old;
    }


}
