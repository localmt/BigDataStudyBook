package com.example.comparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.example.writablecomparable.TextPair;

public class MR_Join_Comparator extends WritableComparator {

    public MR_Join_Comparator() {
        super(TextPair.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TextPair t1 = (TextPair) a;
        TextPair t2 = (TextPair) b;
        return t1.getFirst().compareTo(t2.getFirst());
    }
    
    
}
