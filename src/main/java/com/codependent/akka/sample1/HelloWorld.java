package com.codependent.akka.sample1;

import com.codependent.akka.sample1.Greeter.Msg;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class HelloWorld extends AbstractActor {

	  public HelloWorld() {
	    receive(ReceiveBuilder.
	      matchEquals(Msg.DONE, m -> {
	        // when the greeter is done, stop this actor and with it the application
	        context().stop(self());
	      }).build());
	  }

	  @Override
	  public void preStart() {
	    // create the greeter actor
	    final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
	    // tell it to perform the greeting
	    greeter.tell(Greeter.Msg.GREET, self());
	  }
}