package br.com.autoservice.mb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.autoservice.controller.PecaController;
import br.com.autoservice.modelo.Peca;

@FacesConverter("PecaConverter")
public class PecaConverter implements Converter {
	PecaController service = PecaController.getInstance();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	
            	if (value != null && !value.isEmpty()) {
        			return (Peca) component.getAttributes().get(value);
        		}
        		return null;
            	
            } catch(NumberFormatException e) {
                throw new ConverterException();
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(object);
        }
        else {
            return null;
        }
	}
}