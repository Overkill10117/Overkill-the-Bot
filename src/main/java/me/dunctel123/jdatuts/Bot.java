package me.dunctel123.jdatuts;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.dunctel123.jdatuts.database.DatabaseManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    private Bot() throws LoginException {
        DatabaseManager.INSTANCE.getPrefix(-1);
        WebUtils.setUserAgent("I am a bot");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(0x3883d9)
                        .setFooter("lol")
        );


        EventWaiter waiter = new EventWaiter();
        JDABuilder.createDefault(Config.get("token"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_INVITES
        )
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Listener(waiter), waiter)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setActivity(Activity.playing("Minecraft || type ,help"))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }

}
