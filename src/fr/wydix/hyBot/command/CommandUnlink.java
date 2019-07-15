package fr.wydix.hyBot.command;

import java.io.IOException;

import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandUnlink extends Command {

	public CommandUnlink() {
		super("unlink","unlink a news to channel");
	}

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) throws IOException {
		if(args.length != 0) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" unlink don'take any arguments").queue();
			return;
		}
		if(!event.getGuild().getOwner().equals(event.getMember())) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" only guild owner can execute this command").queue();
			event.getGuild().getOwner().getUser().openPrivateChannel().queue(channel -> channel.sendMessage(event.getAuthor().getAsMention()+" try to change a linked channel, go see and check why.\n"+event.toString()));
			return;
		}
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
