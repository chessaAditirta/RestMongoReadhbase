package com.chessa.example.mongorest.RestMongoReadhbase;

import com.mongodb.*;

import java.util.Arrays;

public class TestRetrieveAllData {

    public static void main( String args[] ) {

//        // Creating a Mongo client
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
                .put("waktu").greaterThan("18:00:00")
                .lessThan("21:00:00").get();
//                .and("dup").exists(false).get();

        DBObject match = new BasicDBObject("$match",query);
        DBObject group = new BasicDBObject("$group",
                new BasicDBObject("_id", "$username")
                        .append("count", new BasicDBObject("$sum",1))
        );

        AggregationOutput output = collection.aggregate(Arrays.asList(match, group));

        for ( DBObject result : output.results() ) {
            System.out.println(result);
        }
    }
}
