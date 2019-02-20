package aula;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SalvarXML {
		
		private static String converter(Document document) throws TransformerException{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(document);
			transformer.transform(source, result);
			
			String xmlString = result.getWriter().toString();
			
			System.out.println(xmlString);
			
			return xmlString;
			
		}
		
		private static void salvarArquivo(String documento)throws Exception{
						
			BufferedWriter bw = null;			
			FileWriter fw = null;
			
			try {				
				fw = new FileWriter("Contatos.xml");
				bw = new BufferedWriter(fw);
				bw.write(documento);
				
			}catch(IOException e) {
				
				System.out.println("Erro");
				
			}finally {
				try {
					if(bw != null) {
						bw.close();
					}
					
					if(fw != null) {
						fw.close();
					}				
					
				}catch(IOException ex) {
					System.out.println("Erro");
				}
			}
			
		}
		
		private static void gerarXml(Cadastro cadastro) throws Exception{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element tagcadastro = doc.createElement("Cadastro");
			
			Element nome = doc.createElement("nome");
			Element tagTelefone = doc.createElement("telefone");
			
			Element operadora = doc.createElement("operadora");
			Element numero = doc.createElement("numero");
			
			nome.setTextContent(cadastro.getNome());
			operadora.setTextContent(cadastro.getTelefone().getOperadora());
			numero.setTextContent(cadastro.getTelefone().getNumero());
			
			tagTelefone.appendChild(operadora);
			tagTelefone.appendChild(numero);
			
			tagcadastro.appendChild(nome);
			tagcadastro.appendChild(tagTelefone);
			
			doc.appendChild(tagcadastro);
			
			String arquivo = converter(doc);
			
			salvarArquivo(arquivo);
			
			
			
		}
		
		public static void main(String[] args) {
			
			Cadastro c = new Cadastro();
			Telefone t = new Telefone();
			t.setNumero("987654321");
			t.setOperadora("Operadora Telefonia");
			c.setNome("Anderson");
			c.setTelefone(t);
			
			try {
				gerarXml(c);
			} catch (Exception e) {				
				e.printStackTrace();				
			}
			
		}
	
	}

	class Telefone{
		private String operadora;
		private String numero;
		
		public String getOperadora() {
			return operadora;
		}
		
		public void setOperadora(String operadora) {
			this.operadora = operadora;
		}
		
		public String getNumero() {
			return numero;
		}
		
		public void setNumero(String numero) {
			this.numero = numero;
		}		
	}
	
	class Cadastro{
		private String nome;
		private Telefone telefone;
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Telefone getTelefone() {
			return telefone;
		}
		public void setTelefone(Telefone telefone) {
			this.telefone = telefone;
		}		
		
		
	}
