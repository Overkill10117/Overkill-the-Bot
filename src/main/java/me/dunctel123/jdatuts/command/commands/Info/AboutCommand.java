package me.dunctel123.jdatuts.command.commands.Info;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.Config;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class AboutCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setTitle("About the Bot")
                .addField("Creator:", "`Overkill#9510`", true)
                .addField("About: ", "Bot of Overkill#9510 made for entertainment and fun ", true)
                .addField("Other Bots", "[Overkill Beta](https://discord.com/api/oauth2/authorize?client_id=775266479725740032&permissions=8&redirect_uri=https%3A%2F%2Fmee6.xyz%2Fdashboard%2F769175521971798036%2Freaction_roles&scope=bot) [Spam Bot](https://discord.com/api/oauth2/authorize?client_id=795555997337780224&permissions=8&scope=bot) [Spam Bot 2](https://discord.com/api/oauth2/authorize?client_id=783925909987131402&permissions=8&scope=bot) [Ultimate Spam Bot](https://discord.com/api/oauth2/authorize?client_id=796183759128100864&permissions=8&scope=bot) [Spam Bot Special](https://discord.com/oauth2/authorize?client_id=816125801694756874&permissions=8&scope=bot)", true)
                .setDescription("BOT")
                .setAuthor("Overkill#9150", null, "https://images-ext-1.discordapp.net/external/_9se3q9TrS_QcFWKGn1G0lxPGSkGpx5kdzFH8KX_jxY/https/cdn.discordapp.com/avatars/766501021144711178/197dd8b8461e72e92707fffdbd81b0a8.png")
                .setThumbnail("https://images-ext-1.discordapp.net/external/_9se3q9TrS_QcFWKGn1G0lxPGSkGpx5kdzFH8KX_jxY/https/cdn.discordapp.com/avatars/766501021144711178/197dd8b8461e72e92707fffdbd81b0a8.png")
                .setColor(Color.blue);
        ctx.getChannel().sendMessage(embed.build()).queue();
    }

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public String getHelp() {
        return "About Stuff";
    }
}
