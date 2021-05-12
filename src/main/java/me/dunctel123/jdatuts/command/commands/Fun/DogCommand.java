package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class DogCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        WebUtils.ins.getJSONObject("https://random.dog/woof.json").async( (json) -> {
            String url = json.get("url").asText();
            MessageEmbed embed = EmbedUtils.embedImage(url).build();
            ctx.getEvent().getChannel().sendMessage(embed).queue();
        });
    }

    @Override
    public String getName() {
        return "dog";
    }

    @Override
    public String getHelp() {
        return "shows a random dog woof";
    }
}
