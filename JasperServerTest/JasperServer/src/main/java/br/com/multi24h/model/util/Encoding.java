package br.com.multi24h.model.util;

import java.nio.charset.Charset;

public enum Encoding {
	
	UTF8(Charset.forName("UTF-8")), ISO8859(Charset.forName("ISO-8859-1"));
	
	private Charset value;
	
	public Charset getValue() {
		return value;
	}
	
	private Encoding(Charset value) {
		this.value = value;
	}
}
