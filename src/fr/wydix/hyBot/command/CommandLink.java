package fr.wydix.hyBot.command;

import java.io.IOException;

import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLink extends Command{

	public CommandLink() {
		super("link","link a channel to a news Website, only one news by channel");
	}
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) throws IOException, InterruptedException {
		if(args.length != 1) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" link command take only one argument : Youtube,Website or Twitter").queue();
			return;
		}
		if(!event.getGuild().getOwner().equals(event.getMember())) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" only guild owner can execute this command").queue();
			event.getGuild().getOwner().getUser().openPrivateChannel().queue(channel -> channel.sendMessage(event.getAuthor().getAsMention()+" try to change a linked channel, go see and check why.\n"+event.toString()));
			return;
		}
		String raw = args[0];
		if(raw.equalsIgnoreCase("Youtube")) {
			unLink(event);
			SGuild.getSGuild(event.getGuild()).setYoutubeChannel(""+event.getTextChannel().getIdLong());
		}else if(raw.equalsIgnoreCase("Website")) {
			unLink(event);
			SGuild.getSGuild(event.getGuild()).setWebsiteChannel(""+event.getTextChannel().getIdLong());
		}else if(raw.equalsIgnoreCase("Twitter")) {
			unLink(event);
			SGuild.getSGuild(event.getGuild()).setTwitterChannel(""+event.getTextChannel().getIdLong());
		}else {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" link command take only : Youtube,Website or Twitter in argument").queue();
			return;
		}
		do {
			try {
				event.getTextChannel().deleteMessages(event.getTextChannel().getHistory().retrievePast(100).complete()).submit();
			}catch (Exception e) {break;}
		}while(event.getTextChannel().hasLatestMessage());
		if(raw.equalsIgnoreCase("Youtube")) {
			HyBot.getNewsManager().getNewsList().get(1).init(SGuild.getSGuild(event.getGuild()));
		}else if(raw.equalsIgnoreCase("Website")) {
			HyBot.getNewsManager().getNewsList().get(2).init(SGuild.getSGuild(event.getGuild()));
		}else if(raw.equalsIgnoreCase("Twitter")) {
			HyBot.getNewsManager().getNewsList().get(0).init(SGuild.getSGuild(event.getGuild()));
		}
		event.getChannel().sendMessage(args[0] +" is now link here").queue();
	}
	public void unLink(MessageReceivedEvent event) throws IOException {
		String whatIsUnlink = "";
		if(event.getChannel().getId().equals(SGuild.getSGuild(event.getGuild()).getTwitterChannel())) {
			SGuild.getSGuild(event.getGuild()).setTwitterChannel("");
			whatIsUnlink =  "Twitter";
		}else if(event.getChannel().getId().equals(SGuild.getSGuild(event.getGuild()).getWebsiteChannel())) {
			SGuild.getSGuild(event.getGuild()).setWebsiteChannel("");
			whatIsUnlink =  "Website";
		}else if(event.getChannel().getId().equals(SGuild.getSGuild(event.getGuild()).getYoutubeChannel())) {
			SGuild.getSGuild(event.getGuild()).setYoutubeChannel("");
			whatIsUnlink =  "Youtube";
		}else {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" this channel isn't link to anything").queue();
			return;
		}
		event.getChannel().sendMessage(event.getAuthor().getAsMention()+" this channel was unlink to "+whatIsUnlink).queue();

	}

}
