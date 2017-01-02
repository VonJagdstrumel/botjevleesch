package net.vonjagdstrumel.botjevleesch.action

import groovy.json.JsonSlurper

import java.nio.charset.StandardCharsets
import java.text.Collator
import java.util.stream.Collector
import java.util.stream.Collectors

import net.dv8tion.jda.events.Event
import net.dv8tion.jda.events.message.MessageReceivedEvent
import net.vonjagdstrumel.botjevleesch.AbstractAction
import net.vonjagdstrumel.botjevleesch.Util

class IbSearchAction extends AbstractAction<MessageReceivedEvent> {
    boolean canHandle(Event event) {
        MessageReceivedEvent.class.isInstance(event)
    }

    boolean isTrigger(MessageReceivedEvent event) {
        event.message.content.startsWith("&ib ")
    }

    void process(MessageReceivedEvent event) {
        def currTime = System.currentTimeMillis()

        if(Util.nextIbSearchTime < currTime) {
            Util.nextIbSearchTime = currTime + 1000

            def paramList = event.message.content.substring(4).tokenize(" ")
            def baseUrl = "https://ibsear.ch"
            def charset = StandardCharsets.UTF_8.name()

            if(paramList.size() > 1 && paramList[0] == "e") {
                paramList = paramList.tail()
                baseUrl = "https://ibsearch.xxx"
            }

            def query = URLEncoder.encode("random: site:gelbooru,danbooru " + paramList.join(" ").replaceAll("(random|site):", ""), charset)
            def apiUrl = "${baseUrl}/api/v1/images.json?q=${query}&limit=1"

            def connection = new URL(apiUrl).openConnection()
            connection.setRequestProperty("X-IbSearch-Key", Util.getConfig("ibSearchKey"))
            connection.setRequestProperty("Accept-Charset", charset)
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("User-Agent", " Botjevleesch/1.0 (+https://github.com/VonJagdstrumel/botjevleesch)")

            def scanner = new Scanner(connection.getInputStream())
            def responseBody = scanner.useDelimiter("\\A").next()
            def imageObj = new JsonSlurper().parseText(responseBody).find() as Map

            if(imageObj) {
                event.channel.sendMessage("${baseUrl}/images/${imageObj.id}")
            }
            else {
                event.channel.sendMessage("Nothing found")
            }
        }
    }
}
