package com.codependent.akka.sample1;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.japi.pf.ReceiveBuilder;

public class Main2 {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("Hello");
		ActorRef a = system.actorOf(Props.create(HelloWorld.class),	"helloWorld");
		system.actorOf(Props.create(Terminator.class, a), "terminator");
	}

	public static class Terminator extends AbstractLoggingActor {

		private final ActorRef ref;

		public Terminator(ActorRef ref) {
	      this.ref = ref;
	      getContext().watch(ref);
	      receive(ReceiveBuilder.
	        match(Terminated.class, t -> {
	          log().info("{} has terminated, shutting down system", ref.path());
	          context().system().terminate();
	        }).build());
	    }
	}
}
