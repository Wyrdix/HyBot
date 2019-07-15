package fr.wydix.hyBot.command;

import java.io.IOException;

import fr.wydix.hyBot.HyBot;
import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHelp extends Command{

	String commandList = "";
	public CommandHelp() {
		super("help","show that");
	}

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) throws IOException {
		commandList = "A lot of my commands are only possibly for the guild owner";
		HyBot.getCommandManager().getCommands().forEach(x-> commandList +=
				"\n<"+SGuild.getSGuild(event.getGuild()).getCommandSymbol()+" "+x.getName()+"> "+x.getDescription()
				);
		commandList += "\nI always send new news, in linked channel, I scan for you";
		EmbedBuilder embed = new EmbedBuilder();
		embed.setDescription(commandList);
		event.getTextChannel().sendMessage(embed.build()).queue();
	}
}
