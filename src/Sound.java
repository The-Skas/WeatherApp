package src;

import java.io.File;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.events.*;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.*;

public class Sound 
{   
    public static void main(String args[]){
        final File f = new File(Sound.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(f);
        play(2, 2);
    }
    private static final int SECOND_MS = 1000;
    private static int current_ms = 0;
    private static int pitchValue;
    private static SamplePlayer player;
    public static void play(int pitch, int code)
    {
        String file;
       
        switch(code)
        {
            case 0: file = "res/sounds/xylo.wav";
                    break;
            case 1: file = "res/sounds/trumpet.wav";
                    break;
            case 2: file = "res/sounds/bone.wav";
                    break;
            case 3: file = "res/sounds/rain.wav";
                    break;
            case 4: file = "res/sounds/thunder.wav";
                    break;
            case 5: file = "res/sounds/snow.wav";
                    break;
            case 6: file = "res/sounds/fogHorn.wav";
                    break;
            case 7: file = "res/sounds/creakv2.wav";
                    break;
            default: file = "res/sounds/xylo.wav";
                     break;
        }
	
        AudioContext audioContext = new AudioContext();
        System.out.println(file);
        System.out.println(audioContext);
        Sample sample = SampleManager.sample(file);
        System.out.println(sample);
        player= new SamplePlayer(audioContext, sample);
        
        //Clock
        final Clock clock = new Clock(audioContext, 1000);
        audioContext.out.addDependent(clock);

        //Pitch
        float playbackRate; 
        Sound.pitchValue = pitch;
        playbackRate = semitoneRatio(440, Sound.pitchValue);
        player.getPitchUGen().setValue(playbackRate);

        player.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
        
        //Gain
        Gain gain = new Gain(audioContext, 1, 1.0f);
        gain.addInput(player);
        audioContext.out.addInput(gain);
        
        audioContext.start();
        player.setKillListener(new KillTrigger(gain));  
    }
    
    /*
    centRatio and semitoneRatio come from PitchRatioCalculator
    Code by Robin Fencott, based on calculations from
    http://www.birdsoft.demon.co.uk/music/samplert.htm
    */
    
    public static float centRatio(double initialSamplePitch, double centIncrement)
    {
	double ratio;
        double targetPitch;

        targetPitch = initialSamplePitch * Math.pow(2.0, (centIncrement / 1200.0 ));
        ratio = targetPitch / initialSamplePitch;		
        return (float)ratio;
    }

    public static float semitoneRatio(double initialSamplePitch, double semitoneIncrement)
    {
        return centRatio(initialSamplePitch, semitoneIncrement*100);	
    }
    public static void update(int delta)
    {
        if(current_ms > 100)
        {
            System.out.println("ok in");
            current_ms = 0;
            float playbackRate;
            Sound.pitchValue += 1;
            playbackRate = semitoneRatio(440, Sound.pitchValue);
            player.getPitchUGen().setValue(playbackRate);
            System.out.println("ok out");
        }
        else
        {
            current_ms += delta;
        }
//        float playbackRate;
//            Sound.pitchValue += 0.1;
//            playbackRate = semitoneRatio(440, Sound.pitchValue);
//            player.getPitchUGen().setValue(playbackRate);
    }
}
