package com.chessa.example.mongorest.RestMongoReadhbase;

import java.sql.Timestamp;
import java.util.Date;

public class CobaTimestamp {
    public static void main(String[] args){
        Timestamp stamp = new Timestamp(152349120000L);
        Date date = new Date(stamp.getTime());
        System.out.println(date);
    }
}
