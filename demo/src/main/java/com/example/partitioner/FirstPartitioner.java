package com.example.partitioner;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.example.stock.IntPair;

public class FirstPartitioner extends Partitioner<IntPair, NullWritable> {

    @Override
    public int getPartition(IntPair key, NullWritable value, int numPartitions) {
        // TODO Auto-generated method stub
        return Math.abs(key.getFirst().get()) % numPartitions;
    }


    
}
