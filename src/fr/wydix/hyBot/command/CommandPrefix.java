package fr.wydix.hyBot.command;

import java.io.IOException;

import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPrefix extends Command {

	public CommandPrefix() {
		super("prefix","change the prefix before each commands");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) throws IOException {
		if(!event.getGuild().getOwner().equals(event.getMember())) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()+" only guild owner can execute this command").queue();
			event.getGuild().getOwner().getUser().openPrivateChannel().queue(channel -> channel.sendMessage(event.getAuthor().getAsMention()+" try to change a my prefix on "+event.getGuild().getName()+", go see and check why.\n"+event.toString()));
			return;
		}
		SGuild.getSGuild(event.getGuild()).setCommandSymbol(String.join("", args));
		event.getChannel().sendMessage("Your new prefix is : "+String.join("", args)).queue();
	}

}
