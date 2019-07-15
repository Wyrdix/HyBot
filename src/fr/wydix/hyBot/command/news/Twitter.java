package fr.wydix.hyBot.command.news;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.entities.TextChannel;
import twitter4j.TwitterException;

public class Twitter extends News{

	public Twitter() throws MalformedURLException, TwitterException {
		super("Twitter","https://twitter.com/Hytale");
	}
	@Override
	public void init(SGuild guild) throws IOException {
		TextChannel linkedChannel = guild.getGuild().getTextChannelById(guild.getTwitterChannel());
		if(linkedChannel.hasLatestMessage()) return;
		do {
			try {
				linkedChannel.deleteMessages(linkedChannel.getHistory().retrievePast(100).complete()).submit();
			}catch (Exception e) {break;}
		}while(linkedChannel.hasLatestMessage());
		String html = this.getCurrentHtml();
		String tweetStream = html.split("\\Q<ol class=\"stream-items js-navigable-stream\"\\E")[1].split("stream-footer")[0];
		for(int i = tweetStream.split("<li class=\"js-stream-item stream-item stream-item").length;i>0;i--) {
			String news = tweetStream.split("<li class=\"js-stream-item stream-item stream-item")[i-1];
			if(!news.contains("pin") && news.contains("/Hytale/status/")) {
				linkedChannel.sendMessage("https://twitter.com/Hytale/status/"+news.split("/Hytale/status/")[1].split("\"")[0]).submit();
				
			}
		}
	}
	@Override
	public String getNewsLink() throws IOException {
		String html = this.getCurrentHtml();
		String tweetStream = html.split("\\Q<ol class=\"stream-items js-navigable-stream\"\\E")[1];
		String last = Arrays.asList(tweetStream.split("\\<li class")).stream().filter(x -> !x.contains("pin") && x.contains("permalink")).findFirst().get();
		String status = last.split("\\Qdata-permalink-path=\"\\E")[1].split("\"")[0];
		return "https://twitter.com"+status;
	}
	@Override
	public void writeNews() throws IOException {
		HyBot.getNewsManager().writeInLinkedChannel(getNewsLink(), new String[] {"link","Twitter"});
	}
}
