package pom.amazon.amazon;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	  public static Object[][] getBookData() {

	        JSONParser parser = new JSONParser();

	        try {

	        	FileReader reader =
	        			new FileReader(System.getProperty("user.dir")
	        			+ "/src/test/resources/books.json");

	            JSONObject jsonObject =
	                    (JSONObject) parser.parse(reader);

	            JSONArray booksArray =
	                    (JSONArray) jsonObject.get("books");

	            Object[][] data =
	                    new Object[booksArray.size()][1];

	            for (int i = 0; i < booksArray.size(); i++) {

	                data[i][0] =
	                        booksArray.get(i).toString();
	            }

	            return data;

	        } catch (Exception e) {

	            e.printStackTrace();

	            return null;
	        }
	    }
	}

