package fr.wydix.hyBot.command.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.wydix.hyBot.FileUser;
import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

public class NewsManager {
	private List<News> newsList = new ArrayList<News>();
	private Thread verify = new Thread(new NewsSearch());
	public NewsManager() {}
	public List<News> getNewsList() {
		return newsList;
	}
	public void addNews(News news) {
		newsList.add(news);
	}
	public Thread getVerify() {
		return verify;
	}
	public void writeInLinkedChannel(String link, String[] bal) throws IOException {
		for(SGuild Sguild:HyBot.getSavedGuild()) {
			Guild guild = Sguild.getGuild();
			TextChannel toWrite;
			if(FileUser.read(Sguild.getSaved(), bal) != null) {
				toWrite = guild.getTextChannelById(FileUser.read(Sguild.getSaved(), bal));	
			}else{
				toWrite = guild.getTextChannels().stream().filter(x->x.canTalk()).findFirst().get();
			}
			try {
				
			}catch (IllegalArgumentException e) {
				// TODO: handle exception
			}
			try {
				toWrite.sendMessage(link).queue();
			}catch (NullPointerException e) {
				System.out.println(link);
				FileUser.write(Sguild.getSaved(), bal,"");
				guild.getTextChannels().stream().filter(x->x.canTalk()).findFirst().get().sendMessage(link).queue();
			}
			
		}
	}

}
