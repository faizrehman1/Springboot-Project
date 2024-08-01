package com.journal.japp.service;

import com.journal.japp.entity.JournalEntry;
import com.journal.japp.entity.User;
import com.journal.japp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    public void saveJournal(JournalEntry journalEntry, String userName) {
        User user = userService.findbyUserName(userName);
        journalEntry.setDateTime(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getbyId(ObjectId objectId) {
        return journalEntryRepository.findById(objectId);
    }

    public void deletebyId(ObjectId objectId) {
        journalEntryRepository.deleteById(objectId);
    }

    public void updatebyId(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

}
