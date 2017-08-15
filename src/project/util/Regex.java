package project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static boolean matchFound(String pattern , String text) {
		
		boolean matchFound = false;
		
		Pattern re = Pattern.compile(pattern);
		Matcher m = re.matcher(text);
		
		if(m.find())
			matchFound = true;
		
		
		
		
		return matchFound;
		
		
	}
	
	public static void main(String[] argv) {
		
		System.out.println(matchFound(".*@(gmail|hotmail|yahoo).com" , "t@yahoo.com"));
		System.out.println(matchFound("^01(5|6|7|8)\\d\\d\\d\\d\\d\\d\\d\\d" , "01799008977"));
	
	}

}
