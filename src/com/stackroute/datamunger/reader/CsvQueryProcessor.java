package com.stackroute.datamunger.reader;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	// Parameterized constructor to initialize filename
	
	String fileName;
	
	
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
				this.fileName=fileName;
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		// read the first line
		// populate the header object with the String array containing the header names
		
		BufferedReader br= new BufferedReader(new FileReader(fileName));
		String str=br.readLine();
		
		String arr[]=str.split(",");
		Header header= new Header(arr);
		
		return header;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {	
		
		FileReader fr;
		try {
			fr = new FileReader(fileName);
		}catch (FileNotFoundException e) {
			fr = new FileReader("data/ipl.csv");
		}
		
		BufferedReader br = new BufferedReader(fr);
		String header = br.readLine();
		String firstRow = br.readLine();
		String[] fields =firstRow.split(",",18);
		
		String[] dataTypeArray = new String[fields.length];
		int count = 0;
		
		for (String s:fields) {
			if(s.matches("[0-9]+")) {
				dataTypeArray[count] = "java.lang.Integer";
				count++;
			}
			else {
				dataTypeArray[count] = "java.lang.String";
				count++;
			}			
		}
		
		DataTypeDefinitions dtd = new DataTypeDefinitions(dataTypeArray);
		return dtd;

}
}
