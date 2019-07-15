package fr.wydix.hyBot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import fr.wydix.hyBot.command.CommandHelp;
import fr.wydix.hyBot.command.CommandLink;
import fr.wydix.hyBot.command.CommandManager;
import fr.wydix.hyBot.command.CommandPrefix;
import fr.wydix.hyBot.command.CommandProfile;
import fr.wydix.hyBot.command.CommandUnlink;
import fr.wydix.hyBot.command.news.NewsManager;
import fr.wydix.hyBot.command.news.Twitter;
import fr.wydix.hyBot.command.news.Website;
import fr.wydix.hyBot.command.news.Youtube;
import net.dv8tion.jda.core.entities.Guild;
import twitter4j.TwitterException;

public class HyBot{
	public static File savedServerFile = new File(HyBot.class.getResource("/server/").getPath());
	private static List<SGuild> savedGuild = new ArrayList<SGuild>();
	private static CommandManager commandManager;
	private static NewsManager newsManager;
	private static BufferedWriter buffer;
	public static String JSON = "<name></name>\n"
			+ "<symbol>hy</symbol>"
			+ "\n<link>"
			+ "\n\t<Youtube></Youtube>"
			+ "\n\t<Twitter></Twitter>"
			+ "\n\t<Website></Website>"
			+ "\n</link>";


	// getVerify is the method who start another thread who read news site to see if there's a new one
	public static void main(String[] args) throws LoginException, MalformedURLException, TwitterException {
		savedServerFile.mkdirs();
		newsManager = new NewsManager();
		newsManager.addNews(new Twitter());
		newsManager.addNews(new Youtube());
		newsManager.addNews(new Website());
		commandManager = new CommandManager();
		commandManager.addCommand(new CommandLink());
		commandManager.addCommand(new CommandPrefix());
		commandManager.addCommand(new CommandProfile());
		commandManager.addCommand(new CommandUnlink());
		commandManager.addCommand(new CommandHelp());
		Bot HyBot = new Bot();
		HyBot.build();
		newsManager.getVerify().start();
	}

	public static CommandManager getCommandManager() {
		return commandManager;
	}
	public static NewsManager getNewsManager() {
		return newsManager;
	}
	public static String getJSON() {
		return JSON;
	}
	public static List<SGuild> getSavedGuild() {
		return savedGuild;
	}
	// This method add a new server to the list of saved server, if necesseries create a file and write in JSON String
	public static void addSavedGuild(Guild guild) throws IOException {
		if(!whatFile(guild).exists()) {
			whatFile(guild).createNewFile();
			buffer = new BufferedWriter(new FileWriter(whatFile(guild).getPath()));
			buffer.write(JSON);
			buffer.close();
		}
		FileUser.write(whatFile(guild), new String[]{"name"}, guild.getName());
		savedGuild.add(new SGuild(guild, whatFile(guild)));
	}
	// Saved server have a file with the server ID
	public static File whatFile(Guild guild) {
		return new File(savedServerFile+"\\"+guild.getId()+".txt");}

}
