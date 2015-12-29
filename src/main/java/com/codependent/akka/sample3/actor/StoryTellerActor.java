package com.codependent.akka.sample3.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;

import com.codependent.akka.sample3.actor.AuthorActor.AuthorType;
import com.codependent.akka.sample3.actor.ImageActor.ImageType;
import com.codependent.akka.sample3.actor.StoryActor.StoryType;
import com.codependent.akka.sample3.dto.Story;

public class StoryTellerActor extends UntypedActor{

	private Story story = new Story();
	private Integer count = 3;
	
	@Override
	public void preStart() {
		final ActorRef storyActor = getContext().actorOf(Props.create(StoryActor.class), "storyActor");
		final ActorRef imageActor = getContext().actorOf(Props.create(ImageActor.class), "imageActor");
		final ActorRef authorActor = getContext().actorOf(Props.create(AuthorActor.class), "authorActor");
		storyActor.tell(StoryType.RANDOM, self());
		imageActor.tell(ImageType.RANDOM, self());
		authorActor.tell(AuthorType.RANDOM, self());
	}
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return super.supervisorStrategy();
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Story){
			Story cast = (Story)msg;
			if(cast.getStory()!=null){
				story.setStory(cast.getStory());
				count--;
			}else if(cast.getImg()!=null){
				story.setImg(cast.getImg());
				count--;
			}else if(((Story)msg).getAuthor()!=null){
				story.setAuthor(cast.getAuthor());
				count--;
			}
			if(isFinished()){
				System.out.println(story);
				getContext().stop(self());
				getContext().system().terminate();
			}
		}else{
			unhandled(msg);
		}
	}
	
	private boolean isFinished(){
		return count == 0;
	}
	
}
