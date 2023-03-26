package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.comparator.MR_Join_Comparator;
import com.example.mapper.MR_Join_Mapper;
import com.example.partitioner.MR_Join_Partitioner;
import com.example.reducer.MR_Join_Reducer;
import com.example.writablecomparable.TextPair;

/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool
{
    public static void main( String[] args ) throws Exception
    {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherArgs = parser.getRemainingArgs();
        if (args.length < 3) {
            System.err.println("Usage: MRJoin <in_path_one> <in_path_two> <output>");
            System.exit(2);
        }
        int result = ToolRunner.run(conf, new App(), otherArgs );
        System.exit( result );
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf(),"MRJoin");
        job.setJarByClass(App.class);
        job.setMapperClass(MR_Join_Mapper.class);
        job.setMapOutputKeyClass(TextPair.class);
        job.setMapOutputValueClass(Text.class);
        job.setPartitionerClass(MR_Join_Partitioner.class);
        job.setGroupingComparatorClass(MR_Join_Comparator.class);
        job.setReducerClass(MR_Join_Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        return 	job.waitForCompletion(true)? 0 : 1;
    }

}
