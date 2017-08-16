package project.crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import project.database.tables.UniversityInfo;

public class WikiScraper{




    public WikiScraper(){

    }
    
    


    private  static HashMap<String , String>  getData(String url){


        HashMap<String , String> universityData = new HashMap<String , String>();
        Downloader downloader = new Downloader();
        String html = downloader.getContent(url);

        if(html == "")
            return universityData; // this hash is empty.


        Document doc = Jsoup.parse(html);

        Elements elements = doc.select(".infobox.vcard tbody tr");

        int index = 0;



        for(Element element : elements){
            //System.out.println(element.text());

            Elements temp = element.getElementsByTag("th");

            if(temp.size() == 1){


                // first element found is not english so we just ignore it.
                index++;
                if(index == 1 )
                    continue;
                String key = temp.get(0).text();

                if(key == "")
                    continue;

                String value = element.getElementsByTag("td").text();

                if(value == "")
                    continue;

                universityData.put(key , value);

                
            }
        }


        return universityData;


    }
    
    
    public static UniversityInfo  getInfoFrom(String url) {
    	
    	
    	UniversityInfo universityInfo = null;
    	HashMap<String , String> universityData = getData(url);
    	
    	if(universityData.size() == 0)
    		return null;
    	
    	   Set<Map.Entry<String  , String>> set = universityData.entrySet();
    	   universityInfo = new UniversityInfo();

           for(Map.Entry<String , String> me : set){
        	   
        	 
        	   
        	   if(me.getKey().equals("Other students"))
        		   universityInfo.setOther_students(me.getValue());
        	   
        	   if(me.getKey().equals("Campus"))
        		   universityInfo.setCampus(me.getValue());
        	   
        	   if(me.getKey().equals("Undergraduates"))
        		   universityInfo.setUndergraduates(me.getValue());
        	   
        	   
        	   if(me.getKey().equals("Postgraduates"))
        		   universityInfo.setPostgraduates(me.getValue());
        	   
        	   if(me.getKey().equals("Doctoral students"))
        		   universityInfo.setPostgraduates(me.getValue());
        	   
        	   if(me.getKey().equals("Website"))
        		   universityInfo.setWebsites(me.getValue());
        	   
        	   if(me.getKey().equals("Motto in English"))
        		   universityInfo.setMotto(me.getValue());
        	   
        	   if(me.getKey().equals("Academic staff"))
        		   universityInfo.setAcadamicStuff(me.getValue());
        	   
        	   if(me.getKey().equals("Type"))
        		   universityInfo.setType(me.getValue());
        	 
        	  
        	   
        	   if(me.getKey().equals("Vice-Chancellor"))
        		   universityInfo.setViseChans(me.getValue());
        	   
        	   
        	   if(me.getKey().equals("Established"))
        		   universityInfo.setEstablished(me.getValue());
        	   
        	   
        	   if(me.getKey().equals("Location"))
        		   universityInfo.setLocation(me.getValue());
        	   
        	   if(me.getKey().equals("Administrative_staff"))
        		   universityInfo.setAdminStuffs(me.getValue());
        	   
        	   

            
           }
    	
    	
    	
    	
    	
    	System.out.println(universityInfo.getCampus());
    	return universityInfo;
    }
    
    
    

    


    public static void main(String[] argv){
    	
    	

       
         getInfoFrom("https://en.wikipedia.org/wiki/University_of_Dhaka");


      
    }
    
}