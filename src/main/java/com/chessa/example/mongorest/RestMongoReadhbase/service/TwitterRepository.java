package com.chessa.example.mongorest.RestMongoReadhbase.service;

import com.chessa.example.mongorest.RestMongoReadhbase.model.Twitter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends MongoRepository<Twitter, String>{

}

