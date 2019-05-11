   /*
 Copyright 2014 Ron Coleman
 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:
 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package f1Breakout;

public class Record implements Comparable<Record> {
	protected String name;
	protected int score;
	
	/**
	 * Constructor
	 * @param name Player name
	 * @param score Player score
	 */
	public Record(String name, int score) { 
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Gets player name.
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets player score.
	 * @return Score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Compares for descending order.
	 * @param record Compared record with this one
	 */
	@Override
	public int compareTo(Record record) {
		// If we reverse this, the order will be descending
		return record.score - this.score;
	}
	
	/**
	 * Converts to a string for SOP.
	 * @return String
	 */
	@Override
	public String toString() { 
		return name + " " + score;
	}

}
