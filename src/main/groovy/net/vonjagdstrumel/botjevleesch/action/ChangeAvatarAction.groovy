package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.dv8tion.jda.utils.AvatarUtil
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

import org.apache.commons.io.FileUtils

class ChangeAvatarAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content ==~ /&avatar https?:\/\/\S+/ && Util.isLegit(event.message.author)
    }

    void process(MessageReceivedEvent event) {
        def picUrl = new URL(event.message.content.substring(8))
        def tmpFile = File.createTempFile("botjevleesch", ".jpg")
        FileUtils.copyURLToFile(picUrl, tmpFile)
        event.JDA.accountManager.avatar = AvatarUtil.getAvatar(tmpFile)
        event.JDA.accountManager.update()
    }
}
