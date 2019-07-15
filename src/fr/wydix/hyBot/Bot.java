package fr.wydix.hyBot;

import net.dv8tion.jda.core.JDABuilder;

//Just create a new JDABuilder
public class Bot extends JDABuilder{
	public Bot() {
		this.setToken("NTk1MTQ2OTY5NzgyMTU3MzMy.XShsSw._azee50n4XaAWQg-RbBxVoXENYI");
		this.addEventListener(new EventListener());
		}
	
	}
