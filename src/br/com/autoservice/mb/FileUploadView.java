package br.com.autoservice.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {
 
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void doUpload(FileUploadEvent fileUploadEvent) { 
        UploadedFile uploadedFile = fileUploadEvent.getFile();  
        String fileNameUploaded = uploadedFile.getFileName(); 
        long fileSizeUploaded = uploadedFile.getSize(); 
        String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded              		+"</b><br/>"+
            "Tamanho do Arquivo: <b>"+fileSizeUploaded+"</b>";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("Sucesso", 			                                                                       infoAboutFile));
}
}
