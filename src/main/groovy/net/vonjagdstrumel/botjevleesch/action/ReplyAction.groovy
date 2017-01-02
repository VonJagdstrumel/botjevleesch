package net.vonjagdstrumel.botjevleesch.action

import java.text.Normalizer

import net.dv8tion.jda.Permission
import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction

import org.apache.commons.lang3.StringUtils

class ReplyAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.isMentioned(event.JDA.selfInfo) && !event.private && event.textChannel.checkPermission(event.JDA.selfInfo, Permission.MESSAGE_WRITE)
    }

    void process(MessageReceivedEvent event) {
        def regex = [
            /bonne (nuit|journee|soiree|annee|apres-midi|cuite|branlette)/,
            /joyeux (rosh hashana|hanoucca|noel|anniversaire|yom kippour)/,
            /a (jamais|bientot|plus|demain|la semaine prochaine|ce soir|tout (a l'heure|de suite)|lundi|mardi|mercredi|jeudi|vendredi|samedi|dimanche)/,
            /bonjour|bonsoir|salut|au revoir|adieu|bonnes vacances|joyeuses pacques|bon ramadan/
        ]
        def filteredMessage = event.message.rawContent.tokenize(" ").findAll { !(it ==~ /(<@[0-9]+>|@here|@everyone)/) }.join(" ").toLowerCase()
        def normalizedMessage = Normalizer.normalize(filteredMessage, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")

        if(normalizedMessage ==~ "(" + regex.join("|") + ")") {
            event.channel.sendMessage(StringUtils.capitalize(filteredMessage) + " à toi aussi " + event.author.asMention)
        }
    }
}
