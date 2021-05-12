package me.dunctel123.jdatuts.command.commands.Fun;

import com.jagrosh.jdautilities.examples.doc.Author;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.dunctel123.jdatuts.command.commands.UtilString;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class DeathNoteCommand implements ICommand {
    public final static String GUILD_ONLINE = "<:online:313956277808005120>";
    public final static String GUILD_IDLE = "<:away:313956277220802560>";
    public final static String GUILD_DND = "<:dnd:313956276893646850>";
    public final static String GUILD_OFFLINE = "<:offline:313956277237710868>";

    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage(getHelp()).queue();
        } else {
            List<User> userMention = ctx.getMessage().getMentionedUsers();
            for (User user : userMention) {
                embedUser(user, ctx.getGuild().getMember(user), ctx.getEvent());
            }
        }
    }


    @Override
    public String getName() {
        return "deathnote";
    }

    @Override
    public String getHelp() {
        return "deathnote [person] [how you will kill him/her] `The human whose name is written in this note shall die.`";
    }
    public static String getStatusEmoji(OnlineStatus stat)
    {
        String status = "";
        switch (stat) {
            case ONLINE:
                status = GUILD_ONLINE;
                break;
            case IDLE:
                status = GUILD_IDLE;
                break;
            case DO_NOT_DISTURB:
                status = GUILD_DND;
                break;
            default:
                status = GUILD_OFFLINE;
        }
        return status;
    }

    private void embedUser(User user, Member member, GuildMessageReceivedEvent e) {
        String name, id, dis, nickname, icon, status, statusEmoji, game, join, register;

        icon = e.getAuthor().getEffectiveAvatarUrl();

        /* Identity */
        name = e.getAuthor().getName();

        nickname = member == null || member.getNickname() == null ? "N/A" : member.getEffectiveName();

        /* Status */
        OnlineStatus stat = member == null ? null : member.getOnlineStatus();
        status = stat == null ? "N?A" : UtilString.VariableToString("_", stat.getKey());
        statusEmoji = stat == null ? "" : getStatusEmoji(stat);
        game = stat == null ? "N/A" : member.getActivities().isEmpty() ? "N/A" : member.getActivities().get(0).getName();

        /* Time */
        join = member == null ? "N?A" : UtilString.formatOffsetDateTime(member.getTimeJoined());
        register = UtilString.formatOffsetDateTime(user.getTimeCreated());

        if (e.getMessage().getMentionedMembers().get(0).isOwner()) {
            String stufff = String.format(("%s `died due to` %s"), e.getAuthor().getAsMention(), e.getMessage().getContentRaw().split("\\s+", 3)[2]);
            EmbedBuilder emmbed = EmbedUtils.defaultEmbed()
                    .setAuthor(name, null , icon)
                    .setThumbnail("https://img1.looper.com/img/gallery/ryuk-in-netflixs-death-note-had-to-be-played-by-two-actors/intro-1503169693.jpg")
                    .setTitle("DEATH NOTE")
                    .setDescription(stufff)
                    .setFooter("The human whose name is written in this note shall die.")
                    .setImage("https://media.comicbook.com/2017/03/screen-shot-2017-03-23-at-2-46-03-pm-240311.png");
            e.getChannel().sendMessage(emmbed.build()).queue();
        } else {
            String stuff = String.format(("%s `died due to` %s"), e.getMessage().getMentionedMembers().get(0).getAsMention(), e.getMessage().getContentRaw().split("\\s+", 3)[2]);
            EmbedBuilder embed = EmbedUtils.defaultEmbed()
                    .setAuthor(name, null , icon)
                    .setThumbnail("https://img1.looper.com/img/gallery/ryuk-in-netflixs-death-note-had-to-be-played-by-two-actors/intro-1503169693.jpg")
                    .setTitle("DEATH NOTE")
                    .setDescription(stuff)
                    .setFooter("The human whose name is written in this note shall die.")
                    .setImage("https://media.comicbook.com/2017/03/screen-shot-2017-03-23-at-2-46-03-pm-240311.png");
            e.getChannel().sendMessage(embed.build()).queue();
        }
    }
}
