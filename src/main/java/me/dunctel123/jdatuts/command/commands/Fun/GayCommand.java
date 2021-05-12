package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class GayCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessageFormat("%s is the gayest of all the gays in %s!!!",
                    ctx.getMessage().getAuthor().getAsMention(), ctx.getGuild().getName()).queue();
        }
        else {
            ctx.getChannel().sendMessageFormat("%s is the gayest of all the gays in %s!!!",
                    ctx.getMessage().getMentionedMembers().get(0), ctx.getGuild().getName()).queue();


        }
    }

    @Override
    public String getName() {
        return "gay";
    }

    @Override
    public String getHelp() {
        return "who is gay?";
    }
}
