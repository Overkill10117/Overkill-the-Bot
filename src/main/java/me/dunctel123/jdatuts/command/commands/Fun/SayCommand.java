package me.dunctel123.jdatuts.command.commands.Fun;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.Config;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class SayCommand implements ICommand {
    final String name = getName();
    String prefix = Config.get("prefix");

    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().contains("embed")) {
            String title = String.format("%s",
                    ctx.getArgs());
            String say = String.format("%s",
                    ctx.getEvent().getMessage().getContentRaw().split("\\s+", 4)[3]);
            EmbedBuilder embed = EmbedUtils.defaultEmbed()
                    .setTitle(title)
                    .setFooter("...........")
                    .setDescription(say)
                    .setAuthor(ctx.getAuthor().getAsTag(), null, ctx.getAuthor().getEffectiveAvatarUrl());
            ctx.getChannel().sendMessage(embed.build()).queue(message -> {
                ctx.getMessage().delete().queue();
            });
        } else {
            ctx.getChannel().sendMessageFormat("%s",
                    ctx.getEvent().getMessage().getContentRaw().split("\\s+", 2)[1]).queue(message -> {
                ctx.getMessage().delete().queue();
            });
        }
    }

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public String getHelp() {
        return "tell the bot to say some thing...";
    }
}
