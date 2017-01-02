package net.vonjagdstrumel.botjevleesch

import groovy.sql.Sql

import java.nio.file.Path
import java.nio.file.Paths

import net.dv8tion.jda.audio.player.Player
import net.dv8tion.jda.entities.User

class Util {
    static Path basePath = Paths.get(Util.class.getResource("/").toURI())
    static Path resourcesPath = basePath.resolve("audio")
    static Sql sql = Sql.newInstance("jdbc:sqlite:" + basePath.resolve("config.db"), "org.sqlite.JDBC")
    static Player player = null
    static long nextIbSearchTime = 0

    static boolean isLegit(User user) {
        Util.sql.rows("SELECT id FROM user WHERE legit=1").collect { it.id }.contains(user.id)
    }

    static String getConfig(String key) {
        sql.firstRow("SELECT value FROM config WHERE key=$key")?.value
    }

    static void setConfig(String key, String value) {
        sql.execute("UPDATE config SET value=$value WHERE key=$key")
    }
}
