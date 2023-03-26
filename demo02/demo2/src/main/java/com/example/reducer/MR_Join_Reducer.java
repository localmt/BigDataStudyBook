package com.example.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.example.writablecomparable.TextPair;

public class MR_Join_Reducer extends Reducer<TextPair, Text, Text, Text> {

    @Override
    protected void reduce(TextPair key, Iterable<Text> values, Reducer<TextPair, Text, Text, Text>.Context context)
            throws IOException, InterruptedException {
        Text pid = key.getFirst();
        String desc = values.iterator().next().toString();
        while (values.iterator().hasNext()) {
            context.write(pid, new Text(values.iterator().next().toString() + "\t" + desc));
        }
    }
    
}
