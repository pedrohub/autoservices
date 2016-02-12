package br.com.autoservice.mb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.autoservice.controller.ClienteController;
import br.com.autoservice.modelo.Cliente;

@FacesConverter("ClienteConverter")
public class ClienteConverter implements Converter {
	ClienteController service = ClienteController.getInstance();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	Cliente c = new Cliente();
            	for (Cliente cliente : service.listar()) {
            		if (cliente.getIdCliente().equals(Long.valueOf(value))) {
            			c = cliente;
            		}
				}
            	return 	c;	
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
            return String.valueOf(((Cliente) object).getIdCliente());
        }
        else {
            return null;
        }
	}
}