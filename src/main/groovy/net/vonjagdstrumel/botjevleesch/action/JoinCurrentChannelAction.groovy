package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.entities.VoiceChannel
import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction

class JoinCurrentChannelAction extends AbstractAction<GuildMessageReceivedEvent> {
    boolean canHandle(Event event) {
        GuildMessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(GuildMessageReceivedEvent event) {
        event.message.content == "&join"
    }

    void process(GuildMessageReceivedEvent event) {
        VoiceChannel voiceChannel = event.guild.voiceChannels.find {
            it.users.find { it == event.message.author }
        }

        if (event.guild.audioManager.connected) {
            event.guild.audioManager.closeAudioConnection()
        }

        event.guild.audioManager.openAudioConnection(voiceChannel)
    }
}
