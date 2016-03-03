package grammar;

import java.util.ArrayList;

public class MusicHeader {

	String composerName = "Unknown";
	Integer indexNumber;
	String noteLength = "1/4";
	String meterSignature = "4/4";
	String keySignature = "";
	Integer beatsPerMinute = 100;
	String title;

	public MusicHeader(ArrayList<String> headerArray)
	{
		for(int i=0; i<headerArray.size(); i++) {
			String parts[] = headerArray.get(i).split(":");
			String firstChar = parts[0];
			String body = parts[1];
			
			switch(firstChar) {
			case "X":
				this.setIndexNumber(Integer.parseInt(body));
				break;
			case "T":
				this.setTitle(body);
				break;
			case "C":
				this.setComposerName(body);
				break;
			case "M":
				this.setMeterSignature(body);
				break;
			case "L":
				this.setNoteLength(body);
				break;
			case "K":
				this.setKeySignature(body);
				break;
			case "Q":
				this.setBPM(Integer.parseInt(body));
				break;
			}
		}
	}
	
	private void setComposerName(String composer)
	{
		this.composerName = composer;
	}

	public String getComposerName()
	{
		return this.composerName;
	}
	
	private void setIndexNumber(Integer index)
	{
		this.indexNumber = index;
	}
	
	public Integer getIndexNumber()
	{
		return this.indexNumber;
	}
	
	private void setNoteLength(String length)
	{
		this.noteLength = length;
	}
	
	public Integer getNoteLength()
	{
		return 4; // Need to fix this later
	}
	
	private void setMeterSignature(String meter)
	{
		this.meterSignature = meter;
	}
	
	public String getMeterSignature()
	{
		return this.meterSignature;
	}
	
	private void setKeySignature(String key)
	{
		this.keySignature = key;
	}
	
	public String getKeySignature()
	{
		return keySignature;
	}
	
	private void setBPM(Integer bpm)
	{
		this.beatsPerMinute = bpm;
	}
	
	public Integer getBPM()
	{
		return this.beatsPerMinute;
	}
	
	private void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	public String toString() {
	    return "Title: " + this.getTitle()
	    		+"\r\n"+
	    		"Composer: " + this.getComposerName()
	    		+"\r\n"+
	    		"Index: " + this.getIndexNumber()
	    		+"\r\n"
	    		+"Note Length: " + this.getNoteLength()
	    		+"\r\n"
	    		+"BPM: " + this.getBPM()
	    		+"\r\n"
	    		+"Meter: " + this.getMeterSignature()
	    		+"\r\n"
	    		+"Key: " + this.getKeySignature()
	    		;
	}
}
