package fr.wydix.hyBot.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import fr.wydix.hyBot.SGuild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandManager {

	private List<Command> commands = new ArrayList<>();
	
	public CommandManager() {}
	
	
	public Collection<Command> getCommands() {
		return commands;
	}
	
	public void addCommand(Command command) {
		if(getCommand(command.getName()) != null) throw new IllegalArgumentException("A command with the same name is already registered !");
		commands.add(command);
	}
	
	public void removeCommand(Command command) {
		commands.remove(command);
	}
	
	public Command getCommand(String name) {
		for(Command command : commands) if(command.getName().equalsIgnoreCase(name)) return command;
		return null;
	}
	
	public void invoke(MessageReceivedEvent event) throws IOException, InterruptedException {
		
		if(!event.getMessage().getContentRaw().startsWith(SGuild.getSGuild(event.getGuild()).getCommandSymbol()+ " ")) return;
		List<String> args = event.getMessage().getContentRaw() == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().split(" ")));
		if(args.size() <= 1) return;
		args.remove(0);
		Command command = getCommand(args.get(0));
		args.remove(0);
		if(command == null) return;
		command.onCommand(event, args.toArray(new String[] {}));
	}
}
