package br.com.autoservice.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;



public class GeneratorPDF {

	
	public static void main(String[] args) {
        
		try {
		    String k = "<html><body> This is my Project <hr>teste <b>teste de teste</b><div>teste falante</div><br></br>testes"
		    		+ "<table > <tr><th>Firstname</th><th>Lastname</th><th>Age</th></tr><tr><td>Jill</td>"+
   "<td>Smith</td><td>50</td></tr><tr><td>Eve</td><td>Jackson</td><td>94</td></tr></table><br>teste"
		    		+ "</body></html>";
		    OutputStream file = new FileOutputStream(new File("C:\\logs\\Test.pdf"));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    document.open();
		    HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.parse(new StringReader(k));
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
}
