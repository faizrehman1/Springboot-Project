package com.journal.japp.response;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {

    @NonNull
    private String userName;
    private List<String> roles;

}
