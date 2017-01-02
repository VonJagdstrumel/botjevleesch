package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class ShutdownAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content == "&quit" && Util.isLegit(event.message.author)
    }

    void process(MessageReceivedEvent event) {
        event.JDA.shutdown()
    }
}
