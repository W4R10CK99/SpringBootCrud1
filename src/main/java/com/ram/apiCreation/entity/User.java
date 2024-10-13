package com.ram.apiCreation.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")

@Data
public class User {
    @Id
    private ObjectId id;
    private String userName;
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    

}
