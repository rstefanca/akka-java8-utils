package cz.codingmonkey.akka.japi;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;

/**
 * @author Richard Stefanca
 */
public class SampleActor extends UntypedActor implements ActorUtils {

    public static Props props(ActorRef audience) {
        return Props.create(SampleActor.class, () -> new SampleActor(audience));
    }

    private final ActorRef audience;

    public SampleActor(ActorRef audience) {
        this.audience = audience;
    }


    @Override
    public void onReceive(Object message) throws Exception {
        if (message.equals("Hello akka")) {
            pipe(Futures.successful("That's right! AKKA is legen... wait for it... dary")).to(audience);
        } else {
            replyWith("Hello who?");
        }
    }
}
