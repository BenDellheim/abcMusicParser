package grammar;

import java.util.ArrayList;
import java.util.Arrays;

public class MusicParser {
	ArrayList<String> body = new ArrayList<String>();
	ArrayList<String> headers = new ArrayList<String>();
	ArrayList<String> validHeaderValues = new ArrayList<>(Arrays.asList("C","K","L","M","Q","T","X"));
	String[] contents;

	public MusicParser(String fileContents)
	{
		this.contents  = fileContents.split("\\r?\\n");
		this.headers = this.parseHeader();
		this.body = this.parseBody();		
		this.getBody();
	}

	public MusicHeader getHeaders()
	{
		return new MusicHeader(this.headers);
	}
	
	public MusicBody getBody()
	{
		return new MusicBody(this.body);
	}

	private ArrayList<String> parseBody()
	{
		//iterate over each line in the file
		for(int i=0; i < this.contents.length; i++) {

			if (contents[i].length() == 0) {
				continue;
			}
			String secondChar = this.contents[i].substring(1,2); // Get the first character

			if(secondChar.equals(":")) {
				continue;
			}
			
			contents[i] = contents[i].replaceAll("\\s+","");
			
			body.add(contents[i]);
		}
		return body;
	}
	
	private ArrayList<String> parseHeader()
	{
		//iterate over each line in the file
		for(int i=0; i < this.contents.length; i++) {

			if (contents[i].length() == 0) {
				continue;
			}

			String firstChar = this.contents[i].substring(0,1); // Get the first character

			if (i==0 && !this.contents[0].substring(0, 1).equals("X")) {
				System.out.println("Not a valid header");
			}

			if(!this.validHeaderValues.contains(firstChar)) {
				continue; //Skip invalid headers
			}
			
			headers.add(contents[i]);
			if (firstChar.equals("K")) {
				break;
			}
		}
		return headers;
	}
	
}
