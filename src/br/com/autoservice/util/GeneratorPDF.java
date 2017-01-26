package br.com.autoservice.util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratorPDF {

	
	public static void main(String[] args) {
        // criação do documento
       Document document = new Document();
       try {
          
    	   PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("C:\\log\\PDF_DevMedia.pdf"));
           document.open();
          
           // adicionando um parágrafo no documento
           document.add(new Paragraph("Gerando PDF - Java 2"));	
           
           PdfContentByte cb = writer.getDirectContent();
           cb.beginText();
        // write text centered at 155 mm from left side and 270 mm from bottom
       // cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Our headline", 439, 765, 0);
        // leave text mode
        cb.endText();
         
        // now draw a line below the headline
        cb.setLineWidth(1f); 
        cb.moveTo(0, 755);
        cb.lineTo(595, 755);
        cb.stroke();
        
        Anchor google = new Anchor("Link para o Google");
        google.setReference("http://www.google.com");

        
        Font helvetica8BoldBlue = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
        
     // create a column object
     ColumnText ct = new ColumnText(cb);
      
     // define the text to print in the column
     Phrase myText = new Phrase("Lorem ipsum dolor sit amet, ...", helvetica8BoldBlue);
     ct.setSimpleColumn(myText, 72, 600, 355, 317, 10, Element.ALIGN_LEFT);
     ct.go();
           
}
       catch(DocumentException de) {
           System.err.println(de.getMessage());
       }
       catch(IOException ioe) {
           System.err.println(ioe.getMessage());
       }
       document.close();
   }   
	
}
