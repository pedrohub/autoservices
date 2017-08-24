package br.com.autoservice.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
 







import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import org.xhtmlrenderer.pdf.ITextRenderer;


public class GeneratorPDF {

	public static final String CSS = "tr { text-align: center; } th { background-color: lightgreen; padding: 3px; } td {background-color: lightblue;  padding: 3px; }";
	public static final String HTML = "/table_css.html";
	
	public static void main(String[] args) {
        
		try {
		    String k = "<html><body> This is my Project teste <b>teste de teste</b><div>teste falante</div>testes"
		    		+ "<table style=\"border: 1px solid black\"> <tr><th>Firstname</th><th>Lastname</th><th>Age</th></tr><tr><td>Jill</td>"+
   "<td>Smith</td><td>50</td></tr><tr><td>Eve</td><td>Jackson</td><td>94</td></tr></table>teste"
		    		+ "</body></html>";
		    
		    System.out.println(k.toString());
		    OutputStream file = new FileOutputStream(new File("C:\\logs\\Test.pdf"));
//		    Document document = new Document();
//		    PdfWriter.getInstance(document, file);
//		    document.open();
//		    HTMLWorker htmlWorker = new HTMLWorker(document);
//		    htmlWorker.parse(new StringReader(k));
//		    XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//	                new FileInputStream(k));
//		    document.close();
//		    file.close();
		    //createPdf("C:\\logs\\Test.pdf");
		    ITextRenderer renderer =  new ITextRenderer();
		    renderer.setDocument("http://localhost:8080/autoService/faces/pages/print.xhtml");
		    renderer.layout();
		    renderer.createPDF(file);
		    
		    
		    // step 1
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"2\">");
        sb.append("<tr>");
        sb.append("<th>Sr. No.</th>");
        sb.append("<th>Text Data</th>");
        sb.append("<th>Number Data</th>");
        sb.append("</tr>");
        for (int i = 0; i < 10; ) {
            i++;
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(i);
            sb.append("</td>");
            sb.append("<td>This is text data ");
            sb.append(i);
            sb.append("</td>");
            sb.append("<td>");
            sb.append(i);
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
 
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
        cssResolver.addCss(cssFile);
 
        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
 
        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
 
        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(sb.toString().getBytes()));
 
        // step 5
        document.close();
    }	
	
}
