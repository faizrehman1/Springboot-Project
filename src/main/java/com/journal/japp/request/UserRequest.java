package com.journal.japp.request;

import com.journal.japp.entity.JournalEntry;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NonNull
    private String userName;
    @NonNull
    private String password;

}
