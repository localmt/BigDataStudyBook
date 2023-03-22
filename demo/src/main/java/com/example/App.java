package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.wordcloud.WordMapper;
import com.example.wordcloud.WordReducer;

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
        
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, args[0]);

        job.setMapperClass(WordMapper.class);
        job.setCombinerClass(WordReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setPartitionerClass(HashPartitioner.class);
        job.setReducerClass(WordReducer.class);
        job.setNumReduceTasks(Integer.parseInt(args[2]));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true)?0:1;
    }
}
