package cz.codingmonkey.akka.japi;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static cz.codingmonkey.akka.japi.TellBuilder.*;
import static java.lang.System.*;

/**
 * @author Richard Stefanca
 */
public class App {
    private static final FiniteDuration BUT_WAIT_ONLY_FOR_ONE_SECOND = Duration.create(1, TimeUnit.SECONDS);

    public static void main(String[] args) throws Exception {

        ActorSystem actorSystem = ActorSystem.create("system");

        Inbox inbox = Inbox.create(actorSystem);
        ActorRef me = inbox.getRef();
        ActorRef sampleActor = actorSystem.actorOf(SampleActor.props(me));

        System.out.println("Me: Hello World");
        tell("Hello world").from(me).to(sampleActor);
        out.println("Actor: " + inbox.receive(BUT_WAIT_ONLY_FOR_ONE_SECOND));
        System.out.println("Me: Hello akka");
        tell("Hello akka").from(me).to(sampleActor);
        out.println("Actor: " + inbox.receive(BUT_WAIT_ONLY_FOR_ONE_SECOND));

        actorSystem.shutdown();

    }
}
