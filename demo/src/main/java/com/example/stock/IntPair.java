package com.example.stock;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class IntPair implements WritableComparable<IntPair> {

    private IntWritable first;
    private IntWritable second;

    public void set(IntWritable first, IntWritable second) {
        this.first = first;
        this.second = second;
    }

    

    public IntPair() {
        set(new IntWritable(), new IntWritable());
    }



    public IntPair(int first, int second) {
        set(new IntWritable(first), new IntWritable(second));
    }



    public IntPair(IntWritable first, IntWritable second) {
        set(first, second);
    }



    @Override
    public String toString() {
        return first + "\t" + second;
    }



    @Override
    public int hashCode() {
        return first.hashCode() * second.hashCode();
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntPair other = (IntPair) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        return true;
    }



    @Override
    public void write(DataOutput out) throws IOException {
        // TODO Auto-generated method stub
        first.write(out);
        second.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        // TODO Auto-generated method stub
        first.readFields(in);
        second.readFields(in);
    }

    @Override
    public int compareTo(IntPair o) {
        // TODO Auto-generated method stub
        int cmp = first.compareTo(o.first);
        if (cmp != 0) {
            return cmp;
        }
        return second.compareTo(o.second);
    }

    public IntWritable getFirst() {
        return first;
    }

    public void setFirst(IntWritable first) {
        this.first = first;
    }

    public IntWritable getSecond() {
        return second;
    }

    public void setSecond(IntWritable second) {
        this.second = second;
    }

}
