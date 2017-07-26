package br.com.autoservice.mb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.autoservice.modelo.Veiculo;

@FacesConverter(forClass = Veiculo.class)
public class VeiculoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (Veiculo) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object value) {
		if (value instanceof Veiculo) {
			Veiculo entity = (Veiculo) value;
			if (entity != null && entity instanceof Veiculo
					&& entity.getCodVeiculo() != null) {
				uiComponent.getAttributes().put(
						entity.getCodVeiculo().toString(), entity);
				return entity.getCodVeiculo().toString();
			}
		}
		return "";
	}
}