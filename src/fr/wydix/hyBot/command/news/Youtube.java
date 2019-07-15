package fr.wydix.hyBot.command.news;

import java.io.IOException;
import java.net.MalformedURLException;

import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.entities.TextChannel;

public class Youtube extends News{
	
	public Youtube() throws MalformedURLException {
		super("Youtube","https://www.youtube.com/channel/UCpmThB2TR0XQ8Y8Zv4zgYyA/videos");
	}
	@Override
	public void init(SGuild guild) throws IOException {
		TextChannel linkedChannel = guild.getGuild().getTextChannelById(guild.getYoutubeChannel());
		if(linkedChannel.hasLatestMessage()) return;
		do {
			try {
				linkedChannel.deleteMessages(linkedChannel.getHistory().retrievePast(100).complete()).submit();
			}catch (Exception e) {break;}
		}while(linkedChannel.hasLatestMessage());
		String html = this.getCurrentHtml();
		for(int i = html.split("\\Q/watch?v=\\E").length;i>1;i--) {
			linkedChannel.sendMessage("https://www.youtube.com/watch?v="+html.split("\\Q/watch?v=\\E")[i-1].split("\"")[0]).submit();
		}
		
	}
	@Override
	public String getNewsLink() throws IOException {
		String html = this.getCurrentHtml();
		String watch = html.split("\\Q/watch?v=\\E")[1].split("\"")[0];
		return "https://www.youtube.com/watch?v="+watch;
	}
	
	@Override
	public void writeNews() throws IOException {
		HyBot.getNewsManager().writeInLinkedChannel(getNewsLink(), new String[] {"link","Youtube"});
	}
}
