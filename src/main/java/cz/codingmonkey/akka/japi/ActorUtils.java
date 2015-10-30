package cz.codingmonkey.akka.japi;

import akka.actor.ActorRef;
import akka.actor.UntypedActorContext;
import akka.pattern.Patterns;
import akka.pattern.PipeToSupport;
import scala.concurrent.Future;

/**
 * @author Richard Stefanca
 */
public interface ActorUtils {

    ActorRef getSelf();

    ActorRef getSender();

    UntypedActorContext getContext();

    default void tellToSelf(Object msg) {
        getSelf().tell(msg, ActorRef.noSender());
    }

    default void replyWith(Object msg) {
        getSender().tell(msg, getSelf());
    }

    default TellBuilder tell(Object msg) {
        return TellBuilder.tell(msg).from(getSelf());
    }

    default <T> PipeToSupport.PipeableFuture<T> pipe(Future<T> future) {
        return Patterns.pipe(future, getContext().dispatcher());
    }
}

