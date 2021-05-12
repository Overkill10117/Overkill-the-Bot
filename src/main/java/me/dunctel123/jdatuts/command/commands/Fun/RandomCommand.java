package me.dunctel123.jdatuts.command.commands.Fun;

import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class RandomCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        double meh = Math.random()*10;

        switch ((int) meh) {
            case 1:
                final TextChannel channel = ctx.getChannel();
                WebUtils.ins.getJSONObject("https://apis.duncte123.me/meme").async((json) -> {
                    if (!json.get("success").asBoolean()) {
                        channel.sendMessage("Something went wrong, try again later").queue();
                        System.out.println(json);
                        return;
                    }

                    final JsonNode data = json.get("data");
                    final String title = data.get("title").asText();
                    final String url = data.get("url").asText();
                    final String image = data.get("image").asText();
                    final EmbedBuilder embed = EmbedUtils.embedImageWithTitle(title, url, image);

                    channel.sendMessage(embed.build()).queue();
                });
                return;
            case 2:
                WebUtils.ins.scrapeWebPage("https://api.thecatapi.com/api/images/get?format=xml&results_per_page=1").async( (document) -> {
                    String url = document.getElementsByTag("url").first().html();
                    MessageEmbed embed = EmbedUtils.embedImage(url).build();
                    //TODO: Make a permission check to see if the bot can send embeds if not, send plain text
                    ctx.getEvent().getChannel().sendMessage(embed).queue();
                });
                return;
            case 3:
                WebUtils.ins.getJSONObject("https://random.dog/woof.json").async( (json) -> {
                    String url = json.get("url").asText();
                    MessageEmbed embed = EmbedUtils.embedImage(url).build();
                    ctx.getEvent().getChannel().sendMessage(embed).queue();
                });
                return;
            case 4:
                ctx.getChannel().sendMessage("`(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻(╯°□°）╯︵ ┻━┻" +
                        "(╯°□°）╯︵ ┻━┻ノ( ゜-゜ノ)`").queue();
                return;
            case 5:
                ctx.getChannel().sendMessage("`(╯°□°）╯︵ ┻━┻ノ( ゜-゜ノ)`").queue();
                return;
            case 6:
                final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                        .setImage("https://projectfandom.com/wp-content/uploads/2016/04/Jason-Syringe-e1460954941188.jpg")
                        .setFooter("lollollollollollollollol")
                        .setAuthor("LOL")
                        .setTitle("lol")
                        .setDescription("lollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollollol");

                ctx.getChannel().sendMessage(embed.build()).queue();
                return;
            case 7:

                WebUtils.ins.getJSONObject("https://apis.duncte123.me/joke").async((json) -> {
                    if (!json.get("success").asBoolean()) {
                        ctx.getChannel().sendMessage("Something went wrong, try again later").queue();
                        System.out.println(json);
                        return;
                    }

                    final JsonNode data = json.get("data");
                    final String title = data.get("title").asText();
                    final String url = data.get("url").asText();
                    final String body = data.get("body").asText();

                    final EmbedBuilder embedded = EmbedUtils.defaultEmbed()
                            .setTitle(title, url)
                            .setDescription(body);

                    ctx.getChannel().sendMessage(embedded.build()).queue();
                });
                return;
            case 8:
                final Member target = ctx.getMember();
                final String avatarUrl = target.getUser().getAvatarUrl();

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle(target.getNickname());
                embedBuilder.setImage(avatarUrl);
                embedBuilder.setColor(Color.cyan);
                embedBuilder.setFooter(",help to get some help");

                ctx.getChannel().sendMessage(embedBuilder.build()).queue();
                return;
            default:
                ctx.getMessage().getAuthor().openPrivateChannel().flatMap(
                        privateChannel -> privateChannel.sendMessageFormat("hahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahaha",
                                ctx.getMessage().getAuthor(), ctx.getGuild().getName())).queue();
                ctx.getChannel().sendMessage("hahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahahahahahahahaahahahahahahaahahahaahahaha @everyone").queue();
        }
    }

    @Override
    public String getName() {
        return "random";
    }

    @Override
    public String getHelp() {
        return "tells you random stuff....";
    }
}
