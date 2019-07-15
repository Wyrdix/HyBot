package fr.wydix.hyBot;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import fr.wydix.hyBot.command.news.News;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	//Called a method to know which command is request
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		try {
			HyBot.getCommandManager().invoke(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
	}
	//When start check all connected servers, add and remove file if need
	@Override
	public void onReady(ReadyEvent event) {
		for(News news:HyBot.getNewsManager().getNewsList()) {
			try {
				news.setLastLink(news.getNewsLink());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Stream<Guild> connectedServerGuild = event.getJDA().getGuilds().stream();
		connectedServerGuild.forEach(x -> {
			try {
				HyBot.addSavedGuild(x);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Stream<File> savedServer = Arrays.asList(HyBot.savedServerFile.listFiles()).stream();
		savedServer.filter(x->!event.getJDA().getGuildById(x.getName().substring(0, x.getName().length()-4)).isAvailable()).forEach(x-> x.delete());
		HyBot.getSavedGuild().forEach(x->{
			HyBot.getNewsManager().getNewsList().forEach(y-> {
				try {
					y.init(x);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		});
	System.out.println("The bot is ready all commands,news and other are now loaded");
	}
	
}
