package com.codependent.akka.sample3.actor;

import com.codependent.akka.sample3.dto.Story;

import akka.actor.UntypedActor;

public class StoryActor extends UntypedActor{

	private static String[] stories = {
		"Once upon a time there lived...",
		"In an Italian habour, at the foot of the mountain, lives our friend",
		"A long time ago in a galaxy far, far away...",
		"It was a dark and stormy night, and our friend..."
	};
	
	public enum StoryType {RANDOM}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == StoryType.RANDOM){
			long random = Math.round(Math.random()*(stories.length-1));
			String selected = stories[(int)random];
			Story s = new Story();
			s.setStory(selected);
			getSender().tell(s, getSelf());
			getContext().stop(self());
		}else{
			unhandled(msg);
		}
	}

}
