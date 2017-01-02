package net.vonjagdstrumel.botjevleesch

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.hooks.ListenerAdapter
import net.vonjagdstrumel.botjevleesch.action.*

class EventListener extends ListenerAdapter {
    private List<AbstractAction> actionList = [
        new BlockBotAction(),
        new ReplyAction(),
        new JoinChannelAction(),
        new JoinCurrentChannelAction(),
        new LeaveChannelAction(),
        new ReplaySoundAction(),
        new PlaySoundAction(),
        new StopSoundAction(),
        new DisplaySoundListAction(),
        new GenerateRandomAction(),
        new IbSearchAction(),
        new ShutdownAction(),
        new ClearAction(),
        new ChangeNickAction(),
        new ChangeAvatarAction(),
        new ChangeGameAction(),
        new DisplayHelpAction()
    ]

    void process(Event event) {
        actionList.find {
            if(it.canHandle(event) && it.isTrigger(event)) {
                it.process(event)
                true
            }
        }
    }
    
    void onReady(ReadyEvent event) {
        event.JDA.accountManager.game = Util.getConfig("game")
        event.JDA.accountManager.update()
    }

    void onMessageReceived(MessageReceivedEvent event) {
        process(event)
    }

    void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        process(event)
    }
}
