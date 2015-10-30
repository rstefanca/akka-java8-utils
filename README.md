# akka-java8-utils

Using Java 8 default methods to create helper methods for an UntypedActor.

Example:

<code java>
replyWith("Hello") 
</code>

instead of 

<code java>
getSender().tell("Hello", getSelf())
</code>