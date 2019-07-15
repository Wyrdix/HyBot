package fr.wydix.hyBot.command.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

import fr.wydix.hyBot.SGuild;

public class News {
	private String name;
	private String lastLink;
	private URL newsUrl;

	public News(String name,String newsUrlString) throws MalformedURLException {
		this.name = name;
		this.newsUrl = new URL(newsUrlString);
		this.setLastLink("");
	}
	public String getNewsLink() throws IOException {return null;}
	public void writeNews() throws IOException {}
	public void init(SGuild guild) throws IOException {}
	public String getCurrentHtml() throws IOException {
		URLConnection connected = newsUrl.openConnection();
		connected.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
		connected.connect();
		return (new BufferedReader(new InputStreamReader(connected.getInputStream(),"UTF-8")).lines()).collect(Collectors.joining("\n"));
	}
	
	public URL getNewsUrl() {
		return newsUrl;
	}
	public String getName() {
		return this.name;
	}
	public String getLastLink() {
		return lastLink;
	}
	public void setLastLink(String lastLink) {
		this.lastLink = lastLink;
	}
}
