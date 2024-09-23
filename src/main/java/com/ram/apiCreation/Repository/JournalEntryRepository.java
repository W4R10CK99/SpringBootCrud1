package com.ram.apiCreation.Repository;


import com.ram.apiCreation.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@Component
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
