package com.example.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.stock.IntPair;

public class TheMapper extends Mapper<LongWritable, Text, IntPair, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntPair, NullWritable>.Context context)
            throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        String[] fields = value.toString().split("    ");
        int field1 = Integer.parseInt(fields[0]);
        int field2 = Integer.parseInt(fields[1]);
        IntPair intPair = new IntPair(field1, field2);
        context.write(intPair, NullWritable.get());
    }
    
}
