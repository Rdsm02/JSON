package aula;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class JSONEscrever {
	
	public static void main(String[] args) throws IOException {
		String telefones[] = {"Claro: 987654321", "Vivo: 123456789", "Nextel: 1029384756"};
		String tagTelefone = "Telefone",
			   tagNome = "Nome";
	    JSONObject obj = new JSONObject();

	    obj.put(tagNome, "Anderson");
	    
	    JSONArray lista = new JSONArray();
	    
	    for(String valor: telefones)
	    	lista.add(valor);

	    obj.put(tagTelefone, lista);
	    

	    try (FileWriter file = new FileWriter("contatos3.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

}
