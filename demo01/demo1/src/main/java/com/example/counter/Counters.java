package com.example.counter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

public class Counters {
    public static class MyCounterMap extends Mapper<LongWritable, Text, Text, Text> {
        public static Counter ct = null;

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split("    ");
            if (words.length > 3) {
                ct = context.getCounter("ErrorCouner", "toolong");
                ct.increment(1);
            }
            else if (words.length < 3) {
                ct = context.getCounter("ErrorCouner", "tooshort");
                ct.increment(1);
            }
        }
    }
}
            
        