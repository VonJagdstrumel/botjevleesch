package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.entities.VoiceChannel
import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction

class JoinChannelAction extends AbstractAction<GuildMessageReceivedEvent> {
    boolean canHandle(Event event) {
        GuildMessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(GuildMessageReceivedEvent event) {
        event.message.content ==~ /&join \w+/
    }

    void process(GuildMessageReceivedEvent event) {
        def chanName = event.message.content.substring(6)
        VoiceChannel voiceChannel = event.guild.voiceChannels.find { it.name =~ /(?i)^${chanName}/ }

        if (!voiceChannel) {
            return
        }

        if (event.guild.audioManager.connected) {
            event.guild.audioManager.closeAudioConnection()
        }

        event.guild.audioManager.openAudioConnection(voiceChannel)
    }
}
