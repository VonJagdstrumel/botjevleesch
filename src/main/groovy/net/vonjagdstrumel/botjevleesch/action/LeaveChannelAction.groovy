package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class LeaveChannelAction extends AbstractAction<GuildMessageReceivedEvent> {
    boolean canHandle(Event event) {
        GuildMessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(GuildMessageReceivedEvent event) {
        event.message.content == "&leave"
    }

    void process(GuildMessageReceivedEvent event) {
        if (Util.player) {
            Util.player.stop()
        }

        if (event.guild.audioManager.connected) {
            event.guild.audioManager.closeAudioConnection()
        }
    }
}
