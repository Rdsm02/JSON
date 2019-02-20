package aula;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("unchecked")
public class JSONLer {
	
	public static void main(String[] args) {
		
		JSONParser parser = new JSONParser();		
		
		try {
			Object obj = parser.parse(new FileReader("cadastro.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			String nome = (String) jsonObject.get("Nome");
			JSONArray telefones = (JSONArray) jsonObject.get("Telefones");
			
			System.out.println("Nome" + nome);
			System.out.println("Telefones: ");
			Iterator<String> iterator = telefones.iterator();
			
			while(iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
