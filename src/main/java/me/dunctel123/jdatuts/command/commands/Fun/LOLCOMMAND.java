package me.dunctel123.jdatuts.command.commands.Fun;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class LOLCOMMAND implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setImage("https://projectfandom.com/wp-content/uploads/2016/04/Jason-Syringe-e1460954941188.jpg")
                .setFooter("lollollollollollollollol")
                .setAuthor("LOL")
                .setTitle("lol")
                .setDescription("lollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollol");

        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getName() {
        return "lol";
    }

    @Override
    public String getHelp() {
        return "lol";
    }
}
