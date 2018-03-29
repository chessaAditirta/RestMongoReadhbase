package com.chessa.example.mongorest.RestMongoReadhbase.controller;

import com.chessa.example.mongorest.RestMongoReadhbase.model.Twitter;
import com.chessa.example.mongorest.RestMongoReadhbase.service.TwitterRepository;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
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

    //Get by id
    @GetMapping("/tweets/{id}")
    public ResponseEntity<Twitter> getTweetById(@PathVariable(value = "id") String tweetId){
        Twitter twitter=twitterRepository.findOne(tweetId);
        if (twitter==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(twitter);
    }


    //get all by time range

    @GetMapping("/tweets/{start}/{end}")
    public ArrayList<String> getTweetByTimeRange(@PathVariable(value = "start") String start,
                                                 @PathVariable(value = "end")String end){
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("test");

        ArrayList<String> twitter = new ArrayList<>();
        // get a single collection
        DBCollection collection = db.getCollection("tweet");
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("waktu", new BasicDBObject("$gt", start).append("$lt", end));
//        DBCursor cursor = collection.aggregate();


//        while(cursor.hasNext()) {
////            twitter.add(cursor.next());
//
//            System.out.println(cursor.next());
//        }
        return twitter;
    }
    //get all by time range count by param username or hastag
    @GetMapping("/tweets/{countby}/{start}/{end}")
    public ArrayList<String> getTweetByTimeRange(@PathVariable(value = "countby") String countBy,
                                                 @PathVariable(value = "start") String start,
                                                 @PathVariable(value = "end")String end){
        ArrayList<String> twitter = new ArrayList<>();
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//
//        // Creating Credentials
//        MongoCredential credential;
//        credential = MongoCredential.createCredential("sampleUser", "myDb",
//                "password".toCharArray());
        System.out.println("Connected to the database successfully");
//
//        // Accessing the database
//        MongoDatabase database = mongo.getDatabase("test");
        DB db = mongo.getDB("test");

//        // Retrieving a collection
        DBCollection collection = db.getCollection("tweet");
        System.out.println("Collection sampleCollection selected successfully");
        DBObject query = QueryBuilder.start()
                .put("waktu").greaterThan(start)
                .lessThan(end).get();
//                .and("dup").exists(false).get();

        DBObject match = new BasicDBObject("$match",query);
        DBObject group = new BasicDBObject("$group",
                new BasicDBObject("_id", "$"+countBy+"")
                        .append("count", new BasicDBObject("$sum",1))
        );

        AggregationOutput output = collection.aggregate(Arrays.asList(match, group));

        for ( DBObject result : output.results() ) {
            System.out.println(result);
        }
        return twitter;
    }





    //create post for  dummy data
    @PostMapping("/tweets")
    public Twitter createNewTweets(@Valid @RequestBody Twitter twitter){
        return twitterRepository.save(twitter);
    }

    //edit by id
    @PutMapping("/tweets/{id}")
    public ResponseEntity<Twitter> updateTweet(
            @PathVariable(value = "id")String tweetId, @Valid @RequestBody Twitter newTweet){
         Twitter twitter=twitterRepository.findOne(tweetId);
        if (twitter==null){
            return ResponseEntity.notFound().build();
        }
        twitter.setUsername(newTweet.getUsername());
        twitter.setWaktu(newTweet.getWaktu());
        twitter.setText(newTweet.getText());
        twitter.setHastag(newTweet.getHastag());

        Twitter updateTwitter= twitterRepository.save(twitter);
        return ResponseEntity.ok(updateTwitter);
    }

    //delete by id
    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Twitter> deleteTweetById(@PathVariable(value = "id")String tweetId){
        Twitter twitter=twitterRepository.findOne(tweetId);
        if (twitter==null){
            return ResponseEntity.notFound().build();
        }

        twitterRepository.delete(twitter);
        return ResponseEntity.ok().build();
    }
}
