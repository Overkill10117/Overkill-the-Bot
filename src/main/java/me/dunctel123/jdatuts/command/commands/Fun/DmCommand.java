package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;

public class DmCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getMessage().getAuthor().openPrivateChannel().flatMap(
                privateChannel -> privateChannel.sendMessageFormat("hahahahahaahahahahahahaahahahaahahaha",
                        ctx.getMessage().getAuthor(), ctx.getGuild().getName())).queue();
        ctx.getChannel().sendMessage("hahahahahahahahahahahahaha").queue();
    }

    @Override
    public String getName() {
        return "haha";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
