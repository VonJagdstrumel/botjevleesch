package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class DisplaySoundListAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content == "&list"
    }

    void process(MessageReceivedEvent event) {
        def listMessage = Util.resourcesPath.toFile().list().join("\n")
        event.channel.sendMessage("```${listMessage}```")
    }
}
