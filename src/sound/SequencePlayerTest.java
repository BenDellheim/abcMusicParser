package sound;

//import static org.junit.Assert.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

public class SequencePlayerTest {

    /**
     * Play an octave up and back down starting from middle C.
     * addNote(base, tick, duration) schedules a note with pitch value 'base'
     * starting at 'tick' to be played for 'duration' number of ticks.
     * For example, addNote(new Pitch('C').toMidiNote(), 10, 1) plays the middle C
     * at time step 10 for half the duration of a quarter note.
     */

	@Test
	public void testMain() {
        SequencePlayer player;
        try {

        	// First warmup
        	// 16th notes present, so quarter note = 4
            player = new SequencePlayer(140, 4);

            player.addNote(new Pitch('C').toMidiNote(), 0, 4);
            player.addNote(new Pitch('C').toMidiNote(), 4, 4);
            player.addNote(new Pitch('C').toMidiNote(), 8, 3);
            player.addNote(new Pitch('D').toMidiNote(), 11, 1);
            player.addNote(new Pitch('E').toMidiNote(), 12, 4);
            player.addNote(new Pitch('E').toMidiNote(), 16, 3);
            player.addNote(new Pitch('D').toMidiNote(), 19, 1);
            player.addNote(new Pitch('E').toMidiNote(), 20, 3);
            player.addNote(new Pitch('F').toMidiNote(), 23, 1);
            player.addNote(new Pitch('G').toMidiNote(), 24, 8);
	
            player.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

	}
	
	/*
	@Test
	public void testMain() {
        SequencePlayer player;
        try {

            // create a new player, with 120 beats (i.e. quarter note) per
            // minute, with 2 tick per quarter note
            player = new SequencePlayer(120, 2);

            player.addNote(new Pitch('C').toMidiNote(), 0, 1);
            player.addNote(new Pitch('D').toMidiNote(), 1, 1);
            player.addNote(new Pitch('E').toMidiNote(), 2, 1);
            player.addNote(new Pitch('F').toMidiNote(), 3, 1);
            player.addNote(new Pitch('G').toMidiNote(), 4, 1);
            player.addNote(new Pitch('A').toMidiNote(), 5, 1);
            player.addNote(new Pitch('B').toMidiNote(), 6, 1);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 7, 1);
            player.addNote(new Pitch('B').toMidiNote(), 8, 1);
            player.addNote(new Pitch('A').toMidiNote(), 9, 1);
            player.addNote(new Pitch('G').toMidiNote(), 10, 1);
            player.addNote(new Pitch('F').toMidiNote(), 11, 1);
            player.addNote(new Pitch('E').toMidiNote(), 12, 1);
            player.addNote(new Pitch('D').toMidiNote(), 13, 1);
            player.addNote(new Pitch('C').toMidiNote(), 14, 1);

            System.out.println(player);

            // play!
            player.play();

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);

}
