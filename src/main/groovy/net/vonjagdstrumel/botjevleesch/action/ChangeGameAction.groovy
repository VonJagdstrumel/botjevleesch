package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class ChangeGameAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content.startsWith("&game ") && Util.isLegit(event.message.author)
    }

    void process(MessageReceivedEvent event) {
        def game = event.message.content.substring(6)
        event.JDA.accountManager.game = game
        event.JDA.accountManager.update()
        Util.setConfig("game", game)
    }
}
