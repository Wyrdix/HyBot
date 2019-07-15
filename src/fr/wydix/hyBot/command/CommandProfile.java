package fr.wydix.hyBot.command;

import java.awt.Color;
import java.io.IOException;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandProfile extends Command{

	public CommandProfile() {
		super("profile"," show my author information");
	}

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) throws IOException {
		EmbedBuilder embed = new EmbedBuilder();
		embed
			.setTitle("My name is HyBot, I'm created by Wyrdix")
			.setImage("https://avatars2.githubusercontent.com/u/30201346?s=460&v=4")
			.setDescription("https://github.com/Wyrdix/HyBot")
			.setColor(Color.BLACK);
		event.getTextChannel().sendMessage(embed.build()).queue();
	}

}
