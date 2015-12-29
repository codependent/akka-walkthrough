package com.codependent.akka.sample3.actor;

import com.codependent.akka.sample3.dto.Story;

import akka.actor.UntypedActor;

public class AuthorActor extends UntypedActor{

	private static String[] authors = {
		"Miguel de Cervantes",
		"William Shakespeare",
		"Ken Follet",
		"Jack Kerouac",
		"Groucho Marx",
		"J.D. Salinger"
	};

	public enum AuthorType {RANDOM}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == AuthorType.RANDOM){
			long random = Math.round(Math.random()*(authors.length-1));
			String selected = authors[(int)random];
			Story s = new Story();
			s.setAuthor(selected);
			getSender().tell(s, getSelf());
			getContext().stop(self());
		}else{
			unhandled(msg);
		}
	}

}
