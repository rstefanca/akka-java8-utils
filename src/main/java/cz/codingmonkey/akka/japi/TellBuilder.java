package cz.codingmonkey.akka.japi;

import akka.actor.ActorRef;

/**
 * tell("hello").to(getSender())
 * tell("hello").from(getSelf()).to(getSender())
 *
 * @author Richard Stefanca
 */
public class TellBuilder {
    private final Object msg;
    private ActorRef from;

    private TellBuilder(Object msg) {
        this.msg = msg;
    }

    public static TellBuilder tell(Object msg) {
        return new TellBuilder(msg);
    }

    public TellBuilder from(ActorRef from) {
        this.from = from;
        return this;
    }

    public void to(ActorRef to) {
        to.tell(msg, from == null ? ActorRef.noSender() : from);
    }
}
