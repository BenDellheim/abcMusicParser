package player;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import grammar.MusicParser;
import sound.Pitch;
import sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * <p>Your code <b>should not</b> exit the application abnormally using
	 * System.exit()</p>
	 * 
	 * @param file the name of input abc file
	 */
	public static void main(String[] args)
	{
		play("sample_abc/prelude.abc");
	}
	public static void play(String file) {
		try {
			String fileContents = readFile(file, StandardCharsets.UTF_8);
			MusicParser mp = new MusicParser(fileContents);
			try {
				SequencePlayer player = new SequencePlayer(
						mp.getHeaders().getBPM(),
						mp.getHeaders().getNoteLength());

	            ArrayList<String> bars = mp.getBody().getBars();
	            int k=0;
	          System.out.println(bars.get(0));  
	            // Iterate through every bar and add the notes to "player"
	            for(int i=0; i < bars.size();  i++) {
	            	char notes[] = bars.get(i).toCharArray();
	            	for(int j=0; j < notes.length; j++) {
	            		System.out.println(notes[j]);
	            		switch(notes[j])
	            		{
	            		case 'z':
	            		case 'Z':
	            			break; // Skip rests
	            		case 'a':
	            		case 'b':
	            		case 'c':
	            		case 'd':
	            		case 'e':
	            		case 'f':
	            		case 'g':
	            			player.addNote(new Pitch(Character.toUpperCase(notes[j])).transpose(Pitch.OCTAVE).toMidiNote(), k, 1);
		            		k++;
	            			break;
	            		case 'A':
	            		case 'B':
	            		case 'C':
	            		case 'D':
	            		case 'E':
	            		case 'F':
	            		case 'G':
	            			player.addNote(new Pitch(notes[j]).toMidiNote(), k, 1);
		            		k++;
	            			break;
            			default:
            				break;
	            		}
	            	}
	            }
	            // play!
	            player.play();
	            System.out.println("Tada!");
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}

}
