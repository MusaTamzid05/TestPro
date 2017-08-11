package Project;


import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;

// Opencv imports

import static org.bytedeco.javacpp.opencv_core.cvLoad;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_FIND_BIGGEST_OBJECT;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_ROUGH_SEARCH;
import static org.bytedeco.javacpp.helper.opencv_objdetect.cvHaarDetectObjects;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FaceDetector {
	
	static private  CvHaarClassifierCascade classifier;
	static private String cascadePath = "/tmp/frontal_casecade.xml";

	
	
	
	static  private  boolean initCasecade() {
		
		boolean cascadeLoaded = false;
		

		try {
			classifier = new CvHaarClassifierCascade(cvLoad(cascadePath));
			
			if(!classifier.isNull())
				cascadeLoaded = true;	
			
		}catch(Exception e) {
			System.out.println("Problem intializing the cascade.");
			
		}
		
		return cascadeLoaded;
		
		
	}
	
	
	static private boolean loadClassifier() {
		
		boolean cascadeLoaded = initCasecade();
		
		if(cascadeLoaded)
			return true;
	
		if(!FaceDetector.downloadCascade())
			return cascadeLoaded;
		
		
		cascadeLoaded = true;
		
		return cascadeLoaded;
			
		
	}
	
	private static boolean downloadCascade() {
		
		System.out.println("Trying to download cascade.");
		
		boolean cascadeDownload = false;
		URL url = null;
		
		try {
			url  = new URL("https://raw.github.com/Itseez/opencv/2.4.0/data/haarcascades/haarcascade_frontalface_alt.xml");
		}catch(MalformedURLException e) {
			System.out.println("Error when downloading the cascade from the url.");
			
		}
		
		if(url == null)
			return cascadeDownload;
		
		System.out.println("Cascade downloaded.");
		
		if(!FaceDetector.saveCascadeFile(url))
			return cascadeDownload;
		
       
        //cascadePath  = cascadeFile.getAbsolutePath();
        
        System.out.println("New cascade path " + cascadePath);
        
        cascadeDownload = initCasecade();
        
        return cascadeDownload;
        
	}
	
	static IplImage getGrayImageOf(IplImage image) {
		
		IplImage grayImage = IplImage.create(image.width(), image.height(), IPL_DEPTH_8U , 1);
		cvCvtColor(image , grayImage , COLOR_BGR2GRAY);
		
		
		
		return grayImage;
		
	}
	
	public static CvSeq getFaces(IplImage image) {
		
		if(image == null) {
			System.out.println("Cannot get image from a null!!");
			return null;
		}
		
		IplImage grayImage = FaceDetector.getGrayImageOf(image);
		CvMemStorage storage = CvMemStorage.create();
		
		CvSeq faces = cvHaarDetectObjects(grayImage , classifier , storage , 1.1 , 3 , CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH);
		
		
		return faces;
		
	}
	
	
	private static boolean saveCascadeFile(URL url) {
		
		
		boolean fileSaved = false;		
		File tempFile= null;

		try {
			
			tempFile= Loader.extractResource(url, null, "classifier", ".xml");
			
			
		}catch(IOException e) {
			System.out.println("Cascade could not be loaded after downloading.");
			return fileSaved;
		}
		
		
		// move the file to desire location
		
		Path source = Paths.get(tempFile.getAbsolutePath());
		
		try {
			Files.move(source, source.resolveSibling(cascadePath));
		}catch(IOException e) {
			System.out.println("Could not rename the file!");
			return fileSaved;
		}
		
		
		
		fileSaved = true;
		
		return fileSaved;
	}
	
	public static int getFaceCount(String imagePath) {
		int count = 0;
		
		if(classifier == null)
			if(!loadClassifier()) {
				System.out.println("Error loading cascade");
				return count;
			}
		
		System.out.println("Cacade loaded.");
		
		IplImage image = FaceDetector.loadImage(imagePath);
		
		if(image == null)
			return count;
		
		
		CvSeq faces = FaceDetector.getFaces(image);
		
		count = faces.total();
		
		
		
		return count;
	}
	
	static IplImage loadImage(String imageName) {
		
		File imageFile = new File(imageName);
		
		if(!imageFile.exists()) {
			System.out.println("Image file does not exists!!");
			return null;
		}
		
		IplImage image = null;
		try {
			image = cvLoadImage(imageFile.getAbsolutePath());
			
		}catch(NullPointerException e) {
			
			System.out.println("Could not load " + imageName);;
		}
		
		System.out.println("Image loaded successfully.");
		
		return image;
		
		
	}
	
	public static void main(String[] argv) {
		
		
		int count = FaceDetector.getFaceCount("/home/musa/test2.jpg");
		
		System.out.println("total face count " + count);
	}

}
