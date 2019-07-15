package fr.wydix.hyBot.command.news;

import java.io.IOException;

import fr.wydix.hyBot.HyBot;

public class NewsSearch implements Runnable{

	@Override
	public void run() {
		for(News news:HyBot.getNewsManager().getNewsList()) {
			try {
				if(!news.getLastLink().equals(news.getNewsLink())) {
					news.writeNews();
					news.setLastLink(news.getNewsLink());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		run();		
	}

}
