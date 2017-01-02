package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.audio.player.FilePlayer
import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class PlaySoundAction extends AbstractAction<GuildMessageReceivedEvent> {
    boolean canHandle(Event event) {
        GuildMessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(GuildMessageReceivedEvent event) {
        event.message.content ==~ /&play \S+/
    }

    void process(GuildMessageReceivedEvent event) {
        if (Util.player) {
            Util.player.stop()
        }

        def audioPrefix = event.message.content.substring(6).replaceAll("(/|\\\\)", "")
        String audioName = Util.resourcesPath.toFile().list().find { it =~ /(?i)^${audioPrefix}/ }

        if(!audioName) {
            return
        }

        def audioFile = Util.resourcesPath.resolve(audioName).toFile()
        Util.player = new FilePlayer(audioFile)
        event.guild.audioManager.sendingHandler = Util.player
        Util.player.play()
    }
}
