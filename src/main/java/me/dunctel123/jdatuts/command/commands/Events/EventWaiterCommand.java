package me.dunctel123.jdatuts.command.commands.Events;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

import java.util.concurrent.TimeUnit;

public class EventWaiterCommand implements ICommand {
    private static final String EMOTE = "😲 ";
    private final EventWaiter waiter;

    public EventWaiterCommand(EventWaiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        channel.sendMessage("React with ")
                .append(EMOTE)
                .queue((message -> {
                    message.addReaction(EMOTE).queue();

                    this.waiter.waitForEvent(
                            GuildMessageReactionAddEvent.class,
                            (e) -> e.getMessageIdLong() == message.getIdLong() && !e.getUser().isBot(),
                            (e) -> {
                                channel.sendMessageFormat("%#S was the first to react", e.getUser()).queue();
                            },
                            5L, TimeUnit.SECONDS,
                            () -> channel.sendMessage("You waited too long").queue()
                    );
                }));
    }

    @Override
    public String getName() {
        return "eventwaiter";
    }

    @Override
    public String getHelp() {
        return "event waiter...";
    }
}
