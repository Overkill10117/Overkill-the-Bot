package me.dunctel123.jdatuts.command.commands.Info;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.List;

public class ServerListCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        List<Guild> list = jda.getGuilds();
        int i = 0;

        while (i < (list.size())) {
            EmbedBuilder embed = EmbedUtils.defaultEmbed()
                    .setColor(Color.RED)
                    .setTitle("Servers.....")
                    .setDescription("This bot is in " + list.get(i));
            i++;
            ctx.getChannel().sendMessage(embed.build()).queue();
        }
    }

    @Override
    public String getName() {
        return "serverlist";
    }

    @Override
    public String getHelp() {
        return "list of servers the bot is in";
    }
}
