package com.example.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import com.example.writablecomparable.TextPair;

public class MR_Join_Partitioner extends Partitioner<TextPair, Text> {

    @Override
    public int getPartition(TextPair key, Text value, int numPartitions) {
       return Math.abs(key.getFirst().hashCode() * 127) % numPartitions;
    }
    
}
