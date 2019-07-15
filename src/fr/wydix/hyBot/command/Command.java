package fr.wydix.hyBot.command;

import java.io.IOException;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command{

	private String name;
	private String description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description  = description;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public abstract void onCommand(MessageReceivedEvent event, String[] args) throws IOException;
}
