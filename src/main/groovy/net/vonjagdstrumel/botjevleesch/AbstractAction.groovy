package net.vonjagdstrumel.botjevleesch

import net.dv8tion.jda.events.Event


abstract class AbstractAction<T extends Event> {
    abstract boolean canHandle(Event event);

    boolean isTrigger(T event) {
        true
    }

    void process(T event){
    }
}
