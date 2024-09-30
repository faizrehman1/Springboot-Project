package com.journal.japp.controller;


import com.journal.japp.entity.JournalEntry;
import com.journal.japp.entity.User;
import com.journal.japp.service.JournalEntryService;
import com.journal.japp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal API",description = "Journal related API's")

public class JournalEntryController {


  //  private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName) {
        User user = userService.findbyUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName) {

        try {
            journalEntryService.saveJournal(journalEntry,userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(journalEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntrybyId(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry =  journalEntryService.getbyId(myId);
        if(journalEntry.isPresent()){
          return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntrybyId(@PathVariable ObjectId myId, @PathVariable String userName){
         journalEntryService.deletebyId(myId,userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntrybyId(@PathVariable ObjectId myId, @RequestBody JournalEntry journalEntry, @PathVariable String userName){

        JournalEntry old = journalEntryService.getbyId(myId).orElse(null);

        if(old !=null){
            old.setName(journalEntry.getName() !=null && !journalEntry.getName().equals("") ? journalEntry.getName() : old.getName());
            old.setDescription(journalEntry.getDescription() !=null && !journalEntry.getDescription().equals("") ? journalEntry.getDescription() : old.getDescription());
            journalEntryService.updatebyId(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
