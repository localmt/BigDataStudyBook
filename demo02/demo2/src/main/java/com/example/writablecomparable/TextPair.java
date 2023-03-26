package com.example.writablecomparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair> {

	private Text first;
	private Text second;

    public void set(Text first, Text second) {
        this.first = first;
        this.second = second;
    }

	public TextPair() {
        set(new Text(), new Text());
    }

    public TextPair(Text first, Text second) {
		set(first, second);
	}

    

	public TextPair(String first, String second) {
        set(new Text(first), new Text(second));
    }

    public Text getFirst() {
		return first;
	}

	public Text getSecond() {
		return second;
	}

	@Override
	public int compareTo(TextPair o) {
		int c = first.compareTo(o.first);
		if (c!= 0) {
			return c;
		}
		return second.compareTo(o.second);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof TextPair)) {
			return false;
		}
		TextPair tp = (TextPair) o;
		return first.equals(tp.first) && second.equals(tp.second);
	}

	@Override
	public String toString() {
		return first + " " + second;
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

}