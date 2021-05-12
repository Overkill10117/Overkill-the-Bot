package me.dunctel123.jdatuts.command.commands.Fun;

import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class UrbanCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage(getHelp()).queue();
            return;
        }
        TextChannel channel = ctx.getChannel();
        final String term = ctx.getArgs().get(0);
        final String url = "http://api.urbandictionary.com/v0/define?term=" + term;
       String webUrl = "https://www.urbandictionary.com/define.php?term=" + term;
        WebUtils.ins.getJSONObject(url).async((json) -> {
            if (json.get("list").isEmpty()) {
                channel.sendMessage("Nothing found").queue();
                return;
            }

            final JsonNode item = json.get("list").get(0);
            final String permaLink = item.get("permalink").asText();

            final EmbedBuilder builder = EmbedUtils.defaultEmbed()
//                    .setTitle("term", webUrl)
                    .setAuthor("Author: " + item.get("author").asText())
                    .setDescription("_TOP DEFINITION:_\n\n")
                    .appendDescription(item.get("definition").asText())
                    .appendDescription("\n\n")
                    .addField("Example", item.get("example").asText(), false)
                    .addField("Upvotes:", String.valueOf(item.get("thumbs_up").asInt()), true)
                    .addField("Downvotes:", String.valueOf(item.get("thumbs_down").asInt()), true)
                    .addField("Link:", "[" + permaLink + "](" + permaLink + ")", false);
            channel.sendMessage(builder.build()).queue();
        });

    }


    @Override
    public String getName() {
        return "urban";
    }

    @Override
    public String getHelp() {
        return "urban [something]";
    }
}
