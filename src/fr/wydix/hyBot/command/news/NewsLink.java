package fr.wydix.hyBot.command.news;

public class NewsLink {
	private String Link;
	private Integer Date;
	public NewsLink(String link,Integer date) {
		this.setLink(link);
		this.setDate(date);
		}
	public Integer getDate() {
		return Date;
	}
	public void setDate(Integer date) {
		Date = date;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
}
