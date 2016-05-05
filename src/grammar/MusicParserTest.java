package grammar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class MusicParserTest {
	
	ArrayList<String> validHeaderValues = new ArrayList<>(Arrays.asList("C","K","L","M","Q","T","X"));

	public ArrayList<String> parseBody(String[] contents)
	{
		ArrayList<String> body = new ArrayList<String>();
		//iterate over each line in the file
		for(int i=0; i < contents.length; i++) {

			// Remove comments (% symbol to end of line)
			contents[i] = contents[i].split("%", 2)[0];
			
			// Skip blank lines
			if (contents[i].length() == 0) {
				continue;
			}
//			System.out.println(this.contents[i]);
			
			if(contents[i].length() >= 2)
			{
				// Check if 2nd character is a header command & skip it
				String secondChar = contents[i].substring(1,2);
				if(secondChar.equals(":")) {continue;}
			}
			
			contents[i] = contents[i].replaceAll("\\s+","");
			
			body.add(contents[i]);
		}
		return body;
	}
	
	public ArrayList<String> parseHeader(String[] contents)
	{
		ArrayList<String> headers = new ArrayList<String>();
		//iterate over each line in the file
		for(int i=0; i < contents.length; i++) {

			// Remove comments (% symbol to end of line)
			contents[i] = contents[i].split("%", 2)[0];

			// Skip blank lines
			if (contents[i].length() == 0) {continue;}
			
			// Get the first character
			String firstChar = contents[i].substring(0,1);

			// First line must be the X field
			if (i==0 && !contents[0].substring(0, 1).equals("X")) {
				System.out.println("Not a valid header");
			}

			// Second line must be the T field
			if (i==1 && !contents[1].substring(0, 1).equals("T")) {
				System.out.println("Not a valid header");
			}

			if(!validHeaderValues.contains(firstChar)) {
				continue; //Skip invalid headers
			}
			
			headers.add(contents[i]);
			
			// Last line is the K (key) field
			if (firstChar.equals("K")) {
				break;
			}
		}
		return headers;
	}

	@Test
	public void testMusicParser() {
/*		String path = "scale.abc";
		  throws IOException
		{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  String fileContents = new String(encoded, StandardCharsets.UTF_8);
		}
*/
		String fileContents = "X:1\n" +
							  "T:Simple scale\n" +
							  "C:Unknown\n" +
							  "M:4/4\n" +
							  "L:1/4\n" +
							  "Q:12\n" +
							  "K:C\n" +
							  "C D E F | G A B c |\n" +
							  "%\n" +
							  "Q:10\n" +
							  "c B A G F E D C |";
		String contents[] = fileContents.split("\\r?\\n");
		ArrayList<String> header = parseHeader(contents);
		ArrayList<String> body = parseBody(contents);
		
		System.out.println(header);
		System.out.println(body);
	}

	@Test
	public void testGetBody() {
		fail("Not yet implemented");
	}

}
