package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.MessageHistory
import net.dv8tion.jda.entities.Message
import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class ClearAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content == "&clear" && Util.isLegit(event.message.author)
    }

    void process(MessageReceivedEvent event) {
        def history = new MessageHistory(event.channel).retrieve()
        history.findAll { it.author == event.JDA.selfInfo }.each { it.deleteMessage() }
    }
}
