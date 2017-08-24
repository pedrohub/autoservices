package br.com.autoservice.mb;

import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

@ManagedBean
@SessionScoped
public class PrintMB implements Serializable{

	
	private static final long serialVersionUID = 5014952806860675596L;
	
	public PrintMB(){
		
	}
	
	public void createPdf(){
		
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external =  facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		String url = "http://localhost:8080/autoService/faces/pages/print.xhtml";
		try{
			
			ITextRenderer renderer =  new ITextRenderer();
		    renderer.setDocument(new URL(url).toString());
		    renderer.layout();
		    HttpServletResponse response = (HttpServletResponse) external.getResponse();
		    response.reset();
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition", "inline; filename=\"printfile.pdf\"");
		    OutputStream out = response.getOutputStream();
		    renderer.createPDF(out);
		    
		    
		    
		   // renderer.createPDF(file);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
