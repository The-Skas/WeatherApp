package myweatherapp;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*; 
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;


public class RedFinder 
{      
    public static void main(String[] args)
    {
        controller();
    }
    
    public static void controller()
    { 
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        boolean done = false;
        final int MAX_ATTEMPTS = 20;
        int attempt = 0;
        IplImage frame;
        //cvNamedWindow("Video");     
        //cvNamedWindow("HSV");
                
        //Checks for camera
        while (!done) 
        {
            attempt += 1;
            try {
                    grabber.start();
                    done = true;
                } 
                catch (FrameGrabber.Exception ex) 
                {
                    if (attempt == MAX_ATTEMPTS) 
                    {
                        System.err.println("Camera initialisation aborted. Max attempts reached.");
                    }
                }
        }

        try
        {
            frame = grabber.grab();
            IplImage imgTracking = cvCreateImage(cvGetSize(frame), IPL_DEPTH_8U, 3);
            cvZero(imgTracking);
        }
        catch(FrameGrabber.Exception ex)
        {
            System.err.println("Problem encountered while grabbing frames: check camera");
        }

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
                System.err.println("Problem encountered while grabbing frames: check camera");
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
    
    private static int[] trackObject(IplImage imgThresh)
    {
        int posX = 0;
        int posY = 0;
        int[] coordinates = {-1,-1};
        
        CvMoments moments = new CvMoments();
        cvMoments(imgThresh, moments, 1);
        
        double momX10 = cvGetSpatialMoment(moments, 1, 0); // (x,y)
        double momY01 = cvGetSpatialMoment(moments, 0, 1);// (x,y)
        double area = cvGetCentralMoment(moments, 0, 0);
        
        if(area > 1000)
        {
            posX = (int) (momX10 / area);
            posY = (int) (momY01 / area);
            
            coordinates[0] = posX;
            coordinates[1] = posY;
        }
        return coordinates;  
    }
}
