package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.comparator.KeyComparator;
import com.example.mapper.TheMapper;
import com.example.partitioner.FirstPartitioner;
import com.example.reducer.TheReducer;
import com.example.stock.IntPair;

/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool
{

    public static void main( String[] args ) throws Exception
    {
        int run = ToolRunner.run(new Configuration(), new App(), args);
        System.exit(run);
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), App.class.getSimpleName());
        job.setJarByClass(App.class);
        job.setMapperClass(TheMapper.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        job.setPartitionerClass(FirstPartitioner.class);
        job.setSortComparatorClass(KeyComparator.class);
        job.setReducerClass(TheReducer.class);
        job.setOutputKeyClass(IntPair.class);
        job.setOutputValueClass(NullWritable.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        int reducerNum = 1;
        if (args.length >= 3 && args[2] != null) {
            reducerNum = Integer.parseInt(args[2]);
        }
        job.setNumReduceTasks(reducerNum);
        return job.waitForCompletion(true)?1:2;
    }
}
