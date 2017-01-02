package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction

class GenerateRandomAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content.startsWith("&rand ")
    }

    void process(MessageReceivedEvent event) {
        def upperBound = event.message.content.substring(6) as int
        def randomInt = new Random().nextInt(upperBound + 1)
        event.channel.sendMessage(randomInt as String)
    }
}
