package Project;


import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import static org.bytedeco.javacpp.opencv_core.cvLoad;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bytedeco.javacpp.Loader;

public class FaceDetector {
	
	static private  CvHaarClassifierCascade classifier;
	static private String cascadePath = "/tmp/frontal_casecade.xml";
	//static private String cascadePath = "./frontal_casecade.xml";
	
	
	
	
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
	
	public static int getFaceCount() {
		int count = 0;
		
		if(classifier == null)
			if(!loadClassifier()) {
				System.out.println("Error loading cascade");
				return count;
			}
		
		
		
		
		System.out.println("Cacade loaded.");
		
		return count;
	}
	
	public static void main(String[] argv) {
		
		System.out.println("test");
		int count =  FaceDetector.getFaceCount();
		
	}

}
