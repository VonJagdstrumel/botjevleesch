package net.vonjagdstrumel.botjevleesch

import net.dv8tion.jda.JDABuilder

class Application {
    static void main(String[] args) {
        def jdaBuilder = new JDABuilder()
        jdaBuilder.setBotToken(Util.getConfig("botToken"))
        jdaBuilder.setAutoReconnect(true)
        jdaBuilder.setBulkDeleteSplittingEnabled(false)
        jdaBuilder.addListener(new EventListener())
        jdaBuilder.buildBlocking()
    }
}
