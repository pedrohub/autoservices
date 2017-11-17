package br.com.autoservice.mb;

import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

import br.com.autoservice.modelo.OS;
import br.com.autoservice.modelo.Orcamento;

@ManagedBean
@SessionScoped
public class PrintMB implements Serializable{

	private static final long serialVersionUID = 5014952806860675596L;
	private static OS os;
	private static Orcamento orcamento;
	private Date dataAbertura = new Date();
	
	public PrintMB(){
		
	}
	
	public void createPdfOS(){
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(true);
		    String url = "http://localhost:8080/autoService/faces/pages/printOS.xhtml;JSESSIONID="+ session.getId()+"pdf=true";
		    try {
			    ITextRenderer renderer = new ITextRenderer();
			    renderer.setDocument(new URL(url).toString());
			    renderer.layout();
			    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
			    response.reset();
			    response.setContentType("application/pdf");
			    response.setHeader("Content-Disposition", "inline; filename=\"printfile.pdf\"");
			    OutputStream browserStream = response.getOutputStream();
			    renderer.createPDF(browserStream);
		    } catch (Exception ex) {
		       ex.printStackTrace();
		    }
		    facesContext.responseComplete();
	}
	
	public void createPdfOrcamento(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(true);
	    String url = "http://localhost:8080/autoService/faces/pages/printOrcamento.xhtml;JSESSIONID="+ session.getId()+"pdf=true";
	    try {
		    ITextRenderer renderer = new ITextRenderer();
		    renderer.setDocument(new URL(url).toString());
		    renderer.layout();
		    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		    response.reset();
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition", "inline; filename=\"printfile.pdf\"");
		    OutputStream browserStream = response.getOutputStream();
		    renderer.createPDF(browserStream);
	    } catch (Exception ex) {
	       ex.printStackTrace();
	    }
	    facesContext.responseComplete();
}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
}
