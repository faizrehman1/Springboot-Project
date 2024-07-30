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
  //      return new ArrayList<>(journalEntryMap.values());

        return journalEntryService.getAll();
    }


    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
   //     journalEntryMap.put(journalEntry.getId(), journalEntry);
        journalEntry.setDateTime(LocalDateTime.now());
        journalEntryService.saveJournal(journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getJournalEntrybyId(@PathVariable ObjectId myId){
   //     return journalEntryMap.get(myId);
        return journalEntryService.getbyId(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntrybyId(@PathVariable Long myId){
   //     return journalEntryMap.remove(myId);
        return null;
    }


}
