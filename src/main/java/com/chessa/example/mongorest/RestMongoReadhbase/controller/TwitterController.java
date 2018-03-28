package com.chessa.example.mongorest.RestMongoReadhbase.controller;

import com.chessa.example.mongorest.RestMongoReadhbase.model.Twitter;
import com.chessa.example.mongorest.RestMongoReadhbase.service.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TwitterController
{
    @Autowired
    TwitterRepository twitterRepository;

    //get all tweet sort by date time createdAt with desc direction sort
    @GetMapping("/tweets")
    public List<Twitter> getAllTwitters(){
        Sort sortById = new Sort(Sort.Direction.DESC, "id");

        return twitterRepository.findAll(sortById);
    }

    //Get by most account

    //create post for  dummy data
    @PostMapping("/tweets")
    public Twitter createNewTweets(@Valid @RequestBody Twitter twitter){
        twitter.setCompleted(false);
        return twitterRepository.save(twitter);
    }

}
