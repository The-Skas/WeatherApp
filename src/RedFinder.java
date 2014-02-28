package src;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*; 
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import javax.swing.JOptionPane;


public class RedFinder 
{      
    static OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    static IplImage frame;
    static int cameraResolutionY;
    static int cameraResolutionX;
    //cameraX Resolution to calculate relative to.
    static float scaleX = 1.0f;
    static final float DEFAULT_SIZE = 1280;// the ITL camera resolution
    static int [] coordinates = {-1,-1};
    
    public static void main(String[] args)
    {
        if(initializeCamera())
        {
            controller();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No camera detected!");
        }
    }
    public static float getX()
    {
        return coordinates[0] * getScaleX();
    }
    
    public static int getY()
    {
        return coordinates[1];
    }
    private static float getScaleX()
    {
        return (DEFAULT_SIZE/cameraResolutionX);
    }
    public static boolean initializeCamera()
    {
        boolean done = false;
        final int MAX_ATTEMPTS = 20;
        int attempt = 0;
        
        //Checks for camera
        while (!done) 
        {
            attempt += 1;
            try 
            {
                grabber.start();
                frame = grabber.grab();
                CvSize size = cvGetSize(frame);
                cameraResolutionX = size.height();
                cameraResolutionY = size.width();

             
                done = true;
            } 
            catch(FrameGrabber.Exception ex) 
            {
                if (attempt == MAX_ATTEMPTS) 
                {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        }
        return done;
    }
    
    public static void controller()
    { 
        while(true)
        {
            try
            {
                frame = grabber.grab();
                cvFlip(frame, frame, 1);
                if(frame == null) break;
                
                //smoothing to decrease noise in captured frame
                frame = cvCloneImage(frame);
                cvSmooth(frame, frame, CV_GAUSSIAN, 3);
                
                //Creating Thresholded image
                IplImage imgHSV = cvCreateImage(cvGetSize(frame), IPL_DEPTH_8U, 3);
                cvCvtColor(frame, imgHSV, CV_BGR2HSV);
                IplImage imgThresh = getThresholdedImage(imgHSV);
                cvSmooth(imgThresh, imgThresh, CV_GAUSSIAN, 3);
                
                int[] coordinates = trackObject(imgThresh);
                
                System.out.println(coordinates[0]+ ", " + coordinates[1]);
                
                //DEBUG: displays video capture and HSV windows
                //cvNamedWindow("Video");     
                //cvNamedWindow("HSV");                
                //cvShowImage("HSV", imgThresh);
                //cvShowImage("Video", frame);
                
                cvReleaseImage(imgHSV);
                cvReleaseImage(imgThresh);            
                cvReleaseImage(frame);
                
                int c = cvWaitKey(10);
                if((char)c==27 ) break;
            }
            catch(FrameGrabber.Exception ex)
            {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        
        cvDestroyAllWindows();
    }
    
    private static IplImage getThresholdedImage(IplImage imgHSV)
    {
        IplImage imgThresh = cvCreateImage(cvGetSize(imgHSV), IPL_DEPTH_8U, 1);
        cvInRangeS(imgHSV, cvScalar(140,100,100,0), cvScalar(200,255,255,0), imgThresh);
        return imgThresh;
    }
    
    private static void trackObject(IplImage imgThresh)
    {
        int posX = 0;
        int posY = 0;
        
        CvMoments moments = new CvMoments();
        cvMoments(imgThresh, moments, 1);
        
        double momX10 = cvGetSpatialMoment(moments, 1, 0);// (x,y)
        double momY01 = cvGetSpatialMoment(moments, 0, 1);// (x,y)
        double area = cvGetCentralMoment(moments, 0, 0);
        
        if(area > 1000)
        {
            posX = (int) (momX10 / area);
            posY = (int) (momY01 / area);
            
            RedFinder.coordinates[0] = posX;
            RedFinder.coordinates[1] = posY;
        }
    }
}
