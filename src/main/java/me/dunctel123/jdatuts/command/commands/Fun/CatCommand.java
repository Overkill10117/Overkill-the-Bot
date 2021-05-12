package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class CatCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        WebUtils.ins.scrapeWebPage("https://api.thecatapi.com/api/images/get?format=xml&results_per_page=1").async( (document) -> {
            String url = document.getElementsByTag("url").first().html();
            MessageEmbed embed = EmbedUtils.embedImage(url).build();
            //TODO: Make a permission check to see if the bot can send embeds if not, send plain text
            ctx.getEvent().getChannel().sendMessage(embed).queue();
        });
    }

    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public String getHelp() {
        return "shows a random cat meow";
    }
}
