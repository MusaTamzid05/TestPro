import java.io.File;
import java.net.URL;
import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.indexer.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

public class Demo {
    public static void main(String[] args) throws Exception {
    	
    	 String classifierName = null;
         if (args.length > 0) {
             classifierName = args[0];
         } else {
             URL url = new URL("https://raw.github.com/Itseez/opencv/2.4.0/data/haarcascades/haarcascade_frontalface_alt.xml");
             File file = Loader.extractResource(url, null, "classifier", ".xml");
             file.deleteOnExit();
             classifierName = file.getAbsolutePath();
             System.out.println(classifierName);
         }

         // Preload the opencv_objdetect module to work around a known bug.
         Loader.load(opencv_objdetect.class);

         // We can "cast" Pointer objects by instantiating a new object of the desired class.
         CvHaarClassifierCascade classifier = new CvHaarClassifierCascade(cvLoad(classifierName));
         if (classifier.isNull()) {
             System.err.println("Error loading classifier file \"" + classifierName + "\".");
             System.exit(1);
         }

       
      System.out.println("s");
    }
}