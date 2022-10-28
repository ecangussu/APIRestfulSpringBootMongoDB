package com.ecangussu.springmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	// Método que irá verificar a string passada no endpoint
	// Verificar se houve o uso de espaço por ex, o que deverá ser corrigido
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
