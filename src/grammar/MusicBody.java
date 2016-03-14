package grammar;

import java.util.ArrayList;

public class MusicBody {
	ArrayList<String> bars = new ArrayList<String>();

	public MusicBody(ArrayList<String> contents)
	{
		this.parseBars(contents);
	}

	public ArrayList<String> getBars()
	{
		return this.bars;
	}

	private void parseBars(ArrayList<String> contents)
	{
		for (int i=0; i < contents.size(); i++) {
			// Split bars on the "|" character.
			String tmp[] = contents.get(i).split("\\|");
			
			//Add the new bars to our list
			for(String b : tmp) {
				bars.add(b);
			}			
		}
	}
}
