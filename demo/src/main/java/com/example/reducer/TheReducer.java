package com.example.reducer;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.example.stock.IntPair;

public class TheReducer extends Reducer<IntPair, NullWritable, IntPair, NullWritable> {

    @Override
    protected void reduce(IntPair key, Iterable<NullWritable> values,
            Reducer<IntPair, NullWritable, IntPair, NullWritable>.Context context)
            throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        context.write(key, NullWritable.get());
    }
    
}
