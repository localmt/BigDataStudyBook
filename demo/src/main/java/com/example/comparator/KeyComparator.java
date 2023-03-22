package com.example.comparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.example.stock.IntPair;

public class KeyComparator extends WritableComparator {

    public KeyComparator() {
        super(IntPair.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // TODO Auto-generated method stub
        IntPair ip1 = (IntPair) a;
        IntPair ip2 = (IntPair) b;
        //第一列按升序排序
        int cmp = ip1.getFirst().compareTo(ip2.getFirst());
        if (cmp != 0) {
            return cmp;
        }
        //在第一列相等的情况下，第二列按倒序排列
        return -ip1.getSecond().compareTo(ip2.getSecond());
    }
    
    
}
