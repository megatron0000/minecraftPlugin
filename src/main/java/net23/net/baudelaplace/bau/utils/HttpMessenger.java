package net23.net.baudelaplace.bau.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;

public class HttpMessenger {

    @Deprecated
    public static String oldGet(String urlString) {
	String stuff = "";
	try {
	    String charset = StandardCharsets.UTF_8.name();
	    String param1 = "value1";
	    String param2 = "value2";
	    String query = String.format("param1=%s&param2=%s", URLEncoder.encode(param1, charset),
		    URLEncoder.encode(param2, charset));
	    System.out.println(query);
	    // Criar URL; depois abrir URLConnection
	    URL url = new URL(urlString);
	    URLConnection connection = url.openConnection();

	    // Manipular parâmetros da conexão
	    connection.setRequestProperty("Accept-Charset", charset);
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

	    // Determinar POST
	    connection.setDoOutput(true);

	    // Colocar a query em seu devido lugar
	    try (OutputStream output = connection.getOutputStream()) {
		output.write(query.getBytes(charset));
	    }

	    // Receber resposta
	    // Não sei converter em string
	    InputStream response = connection.getInputStream();
	    // Converter em String
	    String responseString = new BufferedReader(new InputStreamReader(response, StandardCharsets.UTF_8)).lines()
		    .collect(Collectors.joining("\n"));

	    if (responseString != null) {
		stuff = responseString;
	    }
	} catch (java.io.IOException e1) {
	    stuff = e1.getMessage();
	}
	return stuff;
    }

    protected static String scheme = "http";

    protected static String host = "localhost:3000";

    public static String get(String pathWithQuery) throws Exception {
	URI uri;
	HttpGet httpget;
	CloseableHttpResponse response;
	HttpEntity entity;
	String responseString;
	InputStream instream;

	try {
	    uri = new URI(String.format("%s://%s/%s", scheme, host, pathWithQuery));
	    httpget = new HttpGet(uri);
	    try {
		response = HttpClients.createDefault().execute(httpget);
		entity = response.getEntity();
		try {
		    instream = entity.getContent();
		    responseString = new BufferedReader(new InputStreamReader(instream, StandardCharsets.UTF_8)).lines()
			    .collect(Collectors.joining("\n"));
		    instream.close();
		    response.close();
		    return responseString;
		} catch (Exception e) {
		    throw new Exception();
		}
	    } catch (Exception e) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    throw new Exception();
	}
    }

    public static String post(String pathWithQuery) throws Exception {
	try {
	    // Request.Post(String) pode aceitar String em vez de URI pronta
	    return Request.Post(String.format("%s://%s/%s", scheme, host, pathWithQuery))
		    // .bodyForm(Form.form().add("username",
		    // "vip").add("password", "secret").build()).execute()
		    .bodyString("{\"name\": \"Vitor\", \"age\": 18}",
			    ContentType.create("application/json", StandardCharsets.UTF_8))
		    .execute().returnContent().asString(StandardCharsets.UTF_8);
	} catch (Exception e) {
	    throw new Exception();
	}
    }

}
