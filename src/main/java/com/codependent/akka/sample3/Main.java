package com.codependent.akka.sample3;

import com.codependent.akka.sample3.actor.StoryTellerActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("StoryTeller");
		ActorRef storyTeller = system.actorOf(Props.create(StoryTellerActor.class), "storyTeller");
		System.out.println(storyTeller.path());
	}

}
