package net.vonjagdstrumel.botjevleesch.action

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction

class DisplayHelpAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content == "&help"
    }

    void process(MessageReceivedEvent event) {
        event.channel.sendMessage("```"
                + "&join [<channel>]   Join voice channel\n"
                + "&leave              Leave current voice channel\n"
                + "&play [<file>]      Play audio file\n"
                + "&stop               Stop current play\n"
                + "&list               List available audio files\n"
                + "&rand <number>      Generate a random number between 0 and number included\n"
                + "&ib [e] [<query>]   Fetch a random image from IbSearch\n"
                + "&quit               Disconnect from Discord *\n"
                + "&clear              Delete all of my messages *\n"
                + "&nick <nickname>    Change my nickname *\n"
                + "&avatar <url>       Change my avatar *\n"
                + "&game <game>        Change the currently played game *\n"
                + "&help               Show this help\n"
                + "\n"
                + "* Only for legit users\n"
                + "```")
    }
}
