package com.bdsoft.bdceo.spring.appfx;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bdsoft.utils.StringUtil;

public class DatePropertyEditor extends PropertyEditorSupport {

	private String dateFmt;

	@Override
	public String getAsText() {
		return super.getAsText();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtil.isEmpty(dateFmt)) {
			dateFmt = "yyyy/MM/dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(getDateFmt());
		Date date = new Date();
		try {
			date = format.parse(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setValue(date);
	}

	public String getDateFmt() {
		return dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}

}
