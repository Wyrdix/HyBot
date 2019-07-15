package fr.wydix.hyBot.command.news;

import java.io.IOException;
import java.net.MalformedURLException;

import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.entities.TextChannel;

public class Website extends News{

	public Website() throws MalformedURLException {
		super("Website","https://hytale.com/api/blog/post/published");
	}
	public void init(SGuild guild) throws IOException {
		TextChannel linkedChannel = guild.getGuild().getTextChannelById(guild.getWebsiteChannel());
		if(linkedChannel.hasLatestMessage()) return;
		do {
			try {
				linkedChannel.deleteMessages(linkedChannel.getHistory().retrievePast(100).complete()).submit();
			}catch (Exception e) {break;}
		}while(linkedChannel.hasLatestMessage());
		for(int i = this.getCurrentHtml().replace("{\"featured\"", "\n{\"featured\"").split("\n").length;i>1;i--) {
			String html = this.getCurrentHtml().replace("{\"featured\"", "\n{\"featured\"").split("\n")[i-1];
			String year = html.split("\",\"publishedAt\":\"")[1].split("-")[0];
			String month = html.split("\",\"publishedAt\":\"")[1].split("-")[1].replace("0", "");
			String slug = html.split("slug\":\"")[1].split("\",\"")[0];
			linkedChannel.sendMessage("https://hytale.com/news/"+year+"/"+month+"/"+slug).submit();
		}
	}
	public String getNewsLink() throws IOException {
		String html = this.getCurrentHtml().replace("{\"featured\"", "\n{\"featured\"").split("\n")[1];
		String year = html.split("\",\"publishedAt\":\"")[1].split("-")[0];
		String month = html.split("\",\"publishedAt\":\"")[1].split("-")[1].replace("0", "");
		String slug = html.split("slug\":\"")[1].split("\",\"")[0];
		return "https://hytale.com/news/"+year+"/"+month+"/"+slug;
	}

	@Override
	public void writeNews() throws IOException {
		HyBot.getNewsManager().writeInLinkedChannel(getNewsLink(), new String[] {"link","Website"});
	}
}
