package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import com.example.counter.Counters;
/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool
{
    public static void main( String[] args ) throws Exception
    {
        Configuration conf = new Configuration();
        String[] otheArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otheArgs.length != 2) {
            System.out.println("Usage: Counters <in> <out>");
            System.exit(2);
        }
        int run = ToolRunner.run(conf, new App(), otheArgs);
        System.exit(run);
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf(), "Counters");
        job.setJarByClass(App.class);
        job.setMapperClass(Counters.MyCounterMap.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true)? 0 : 1;
    }
}




