package project.examresults;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

public class CapshaSolver {
	
	public static int solve(String html) {
		
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(".black12bold tbody tr");
		
		String text = elements.get(6).text();
		String text2 = text.trim();
		String text3= text2.replace(" " , "");
		System.out.println(text3);
		System.out.println(text3.length());
		
		
		String val1 = "" + text3.charAt(1);
		String val2 = ""  + text3.charAt(3);
		
		int result = Integer.parseInt(val1) + Integer.parseInt(val2);
		return result;
		
	}

}
