package src;

import net.beadsproject.beads.core.*;
import net.beadsproject.beads.events.*;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.data.*;

public class Sound 
{
    public static void main(String args[]){
        play(0, 0);
    }
    
    public static void play(int pitch, int code)
    {
        String file;
       
        switch(code)
        {
            case 0: file = "sounds/xylo.wav";
                    break;
            case 1: file = "sounds/trumpet.wav";
                    break;
            case 2: file = "sounds/bone.wav";
                    break;
            case 3: file = "sounds/rain.wav";
                    break;
            case 4: file = "sounds/thunder.wav";
                    break;
            case 5: file = "sounds/snow.wav";
                    break;
            case 6: file = "sounds/fogHorn.wav";
                    break;
            default: file = "sounds/xylo.wav";
                     break;
        }
	
        AudioContext audioContext = new AudioContext();
        Sample sample = SampleManager.sample(file);
        SamplePlayer samplePlayer = new SamplePlayer(audioContext, sample);
        
        //Clock
        final Clock clock = new Clock(audioContext, 1000);
        audioContext.out.addDependent(clock);

        //Pitch
        float playbackRate;
        playbackRate = PitchRatioCalculator.semitoneRatio(440, pitch);
        samplePlayer.getPitchUGen().setValue(playbackRate);
        
        //Gain
        Gain gain = new Gain(audioContext, 1, 1.0f);
        gain.addInput(samplePlayer);
        audioContext.out.addInput(gain);
        
        audioContext.start();
        samplePlayer.setKillListener(new KillTrigger(gain));  
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
}
