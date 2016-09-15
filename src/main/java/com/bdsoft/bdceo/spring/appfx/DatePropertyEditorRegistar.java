package com.bdsoft.bdceo.spring.appfx;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class DatePropertyEditorRegistar implements PropertyEditorRegistrar {

	private PropertyEditor pe;

	@Override
	public void registerCustomEditors(PropertyEditorRegistry peReg) {
		peReg.registerCustomEditor(Date.class, getPe());
	}

	public PropertyEditor getPe() {
		return pe;
	}

	public void setPe(PropertyEditor pe) {
		this.pe = pe;
	}

}
