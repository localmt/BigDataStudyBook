package com.example.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.example.writablecomparable.TextPair;

public class MR_Join_Mapper extends Mapper<LongWritable, Text, TextPair, Text> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, TextPair, Text>.Context context)
            throws IOException, InterruptedException {
        // 获取输入文件的全路径和名称
        String pathName = ((FileSplit)context.getInputSplit()).getPath().getName();
        if (pathName.contains("data.txt")) {
            String values[] = value.toString().split("    ");
            if (values.length < 3) {
                return;
            } else {
                TextPair tp = new TextPair(new Text(values[1]),new Text("1"));
                context.write(tp, new Text(values[0] + "\t" + values[2]));
            }
        }
        if (pathName.contains("info.txt")) {
            String values[] = value.toString().split("    ");
            if (values.length < 2) {
                return;
            } else {
                TextPair tp = new TextPair(new Text(values[0]),new Text("1"));
                context.write(tp, new Text(values[1]));
            }
        }
    }
    
}
