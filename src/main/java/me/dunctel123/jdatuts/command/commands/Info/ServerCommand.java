package me.dunctel123.jdatuts.command.commands.Info;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;

public class ServerCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<Guild> list = jda.getGuilds();
        ctx.getChannel().sendMessage("This bot is in " + (list.size()) + " servers").queue();
    }

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public String getHelp() {
        return "Usage: `/server`\n" +
                "Returns the count of servers the bot is in";
    }
}
