package me.dunctel123.jdatuts.command.commands.Info;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class AvatarCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            final Member target = ctx.getMember();
            final String avatarUrl = target.getUser().getAvatarUrl();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(target.getNickname());
            embedBuilder.setImage(avatarUrl);
            embedBuilder.setColor(Color.cyan);
            embedBuilder.setFooter(",help to get some help");

            ctx.getChannel().sendMessage(embedBuilder.build()).queue();

        }
        final Member target = ctx.getMessage().getMentionedMembers().get(0);
        final String avatarUrl = target.getUser().getAvatarUrl();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(target.getNickname());
        embedBuilder.setImage(avatarUrl);
        embedBuilder.setColor(Color.cyan);
        embedBuilder.setFooter(",help to get some help");


        ctx.getChannel().sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "avatar";
    }

    @Override
    public String getHelp() {
        return "avatar [guy]";
    }
}

