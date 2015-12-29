package com.codependent.akka.sample3.actor;

import com.codependent.akka.sample3.dto.Story;

import akka.actor.UntypedActor;

public class ImageActor extends UntypedActor{

	private static String[] images = {
		"minion-carl.jpg",
		"minion-dave.jpg",
		"minion-evil.jpg",
		"minion-kevin.jpg",
		"minion-phil.jpg",
		"minion-stuart.jpg",
		"minion-tim.jpg",
		"minion-tom.jpg",
		"minion-wolverine.jpg"
	};
	
	public enum ImageType {RANDOM}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == ImageType.RANDOM){
			long random = Math.round(Math.random()*(images.length-1));
			String selected = images[(int)random];
			Story s = new Story();
			s.setImg(selected);
			getSender().tell(s, getSelf());
			getContext().stop(self());
		}else{
			unhandled(msg);
		}
	}

}
