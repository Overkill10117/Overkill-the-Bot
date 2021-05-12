package me.dunctel123.jdatuts;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.duncte123.botcommons.BotCommons;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.commands.ReactionRoleData;
import me.dunctel123.jdatuts.command.commands.UtilString;
import me.dunctel123.jdatuts.database.DatabaseManager;
import me.dunctel123.jdatuts.database.SQLiteDataSource;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Listener extends ListenerAdapter {
    int seconds;
    Message message;
    String item;

    public void Giveawayy(int time, Message message, String item) {
        seconds = time;
        this.message = message;
        this.item = item;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager;

    public Listener(EventWaiter waiter) {
        manager = new CommandManager(waiter);
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        String join, register;
        join = UtilString.formatOffsetDateTime(event.getMember().getTimeJoined());
        register = UtilString.formatOffsetDateTime(event.getUser().getTimeCreated());

        final int memberCount = event.getGuild().getMemberCount();
        String lol = String.format("Welcome %s to %s you are the " + memberCount + " member",
                event.getMember().getUser().getAsMention(), event.getGuild().getName());
        String a = event.getMember().getTimeJoined().toString();
        String icon = event.getUser().getEffectiveAvatarUrl();
        final EmbedBuilder useGuildSpecificSettingsInstead = EmbedUtils.defaultEmbed()
                .setDescription(lol)
                .addField("Time joined", join, true)
                .addField("Register:", register, true)
                .setThumbnail(icon);


        Objects.requireNonNull(event.getGuild().getSystemChannel()).sendMessage(useGuildSpecificSettingsInstead.build()).queue();
    }

    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        final int memberCount = event.getGuild().getMemberCount();
        String lol = String.format("GoodBye %s we have " + memberCount + " members now",
                event.getUser().getAsMention(), event.getGuild().getName());
        String icon = event.getUser().getEffectiveAvatarUrl();
        if (lol.isEmpty()) {
            return;
        }
        final EmbedBuilder useGuildSpecificSettingsInstead = EmbedUtils.defaultEmbed()
                .setDescription(lol)
                .setThumbnail(icon);


        Objects.requireNonNull(event.getGuild().getSystemChannel()).sendMessage(useGuildSpecificSettingsInstead.build()).queue();

    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        final String useGuildSpecificSettingsInstead = String.format("Thank you for adding %s to %s!!!'n" +
                        "To know about this bot feel free to type ,help\n" +
                        "To know more about a command type ,help [command name]",
                event.getJDA().getSelfUser().getAsMention(), event.getGuild().getName());

        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessage(useGuildSpecificSettingsInstead).queue();
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (ReactionRoleData.messages.contains(event.getMessageIdLong())) {
            final Role role = ReactionRoleData.roles.get(event.getMessageIdLong());
            final User user = event.getUser();
            if (!user.isBot()) {

                user.openPrivateChannel().queue(PrivateChannel ->
                        PrivateChannel.sendMessage(role.getName() + " has been successfully added.").queue(message -> {
                            ReactionRoleData.roles.put(message.getIdLong(), role);
                            event.getGuild().addRoleToMember(user.getIdLong(), role).queue();
                        }));
            }
        }
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        if (ReactionRoleData.messages.contains(event.getMessageIdLong())) {
            final Role role = ReactionRoleData.roles.get(event.getMessageIdLong());
            final User user = event.getUser();
            assert user != null;
            if (!user.isBot()) {
                user.openPrivateChannel().queue(PrivateChannel ->
                        PrivateChannel.sendMessage(role.getName() + " has been removed").queue(message -> {
                            ReactionRoleData.roles.put(event.getMessageIdLong(), role);
                            event.getGuild().removeRoleFromMember(user.getIdLong(), role).queue();
                        }));
            }
        }
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {

        final long guildId = event.getGuild().getIdLong();
        String prefix = VeryBadDesign.PREFIXES.computeIfAbsent(guildId, DatabaseManager.INSTANCE::getPrefix);
        String raw = event.getMessage().getContentRaw();

        User user = event.getAuthor();
        if (user.isBot() || event.isWebhookMessage()) {
        return;
    }


        if (raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.get("owner_id"))) {
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        if (raw.startsWith(prefix)) {
            manager.handle(event, prefix);
        }

    }
}
