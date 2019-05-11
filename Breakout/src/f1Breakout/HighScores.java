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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements the high score facade.
 * @author Ron.Coleman
 *
 */
public class HighScores {
	// path in file where high scores persist
	public String path = null;
	
	// high scores kept in memory in this list
	protected ArrayList<Record> records = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param path Path to high scores file
	 */
	public HighScores(String path) {
		this.path = path;
	}
	
	/**
	 * Loads in the high scores.
	 * @return List of scores as records of name-score pairs per record
	 */
	public ArrayList<Record> load() {
		records.clear();
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			
			String line = reader.readLine();
			
			//load in the two-field records -- all others are skipped
			while(line != null) {
				String[] fields = line.split(" +");
				
				if(fields.length == 2) {
					String name = fields[0];
					
					int score = Integer.parseInt(fields[1]);
					
					records.add(new Record(name, score));
				}
				
				line = reader.readLine();
			}
			
			reader.close();
		}
		catch(FileNotFoundException e) {
	    }
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
			records = null;
		}
		
		return records;

    }
	
	/**
	 * Gets the records as a list.
	 * @return List of records
	 */
	public ArrayList<Record> getRecords() {
		return records;
	}
	
	/**
	 * Saves the records sorted in descending order -- whatever is in records.
	 */
	public int save() {
		Collections.sort(records);
		
		BufferedWriter writer;
		
		int count = 0;
		
		try {
			writer = new BufferedWriter(new FileWriter(path));
			for(Record record: records) {
				writer.write(record.getName() + " " + record.getScore());
				writer.newLine();
				count++;
			}
			
			writer.flush();
			
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void clear() {
		records.clear();
	}
	
	public String toString() {
		String s = "";
		for(Record record: records)
			s += record + "\n";
		
		return s;
	}
	
	
}
