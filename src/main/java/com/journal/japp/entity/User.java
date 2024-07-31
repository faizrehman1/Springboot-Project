package com.journal.japp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@Data
public class User {


    @Id
    private ObjectId objectId;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private LocalDateTime localDateTime;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();


}
