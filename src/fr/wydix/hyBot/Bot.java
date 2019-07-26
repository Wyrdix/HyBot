package fr.wydix.hyBot;

import net.dv8tion.jda.core.JDABuilder;

//Just create a new JDABuilder
public class Bot extends JDABuilder{
	public Bot() {
		this.setToken("/*Enter here the token of your bot*/");
		this.addEventListener(new EventListener());
		}
	
	}
