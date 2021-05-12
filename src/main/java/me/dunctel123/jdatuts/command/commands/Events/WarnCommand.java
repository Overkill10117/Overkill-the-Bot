package me.dunctel123.jdatuts.command.commands.Events;

import me.dunctel123.jdatuts.Config;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.dunctel123.jdatuts.command.commands.Emoji;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.util.List;

public class WarnCommand implements ICommand {
    @Override
    public void handle(CommandContext e) {
        final List<String> args = e.getArgs();
        if(args.size() == 1 && "-h".equals(args.get(0))) {
            e.getChannel().sendMessage(getHelp()).queue();
            return;
        }

        if(e.getMember().hasPermission(Permission.ADMINISTRATOR) ||
                e.getMember().hasPermission(Permission.ADMINISTRATOR) ||
                e.getMember().hasPermission(Permission.MANAGE_SERVER) ||
                e.getMember().hasPermission(Permission.MANAGE_CHANNEL) ||
                Config.get("prefix").equals(e.getAuthor().getId()))
        {
            List<User> mention = e.getMessage().getMentionedUsers();
            if(mention.isEmpty())
                return;

            //Get Names
            String names = "";
            for(User n : mention) { names += n.getName() + "#" + n.getDiscriminator() + ", "; }
            names = names.substring(0,names.length()-2);

            //Get Reason
            String reason = String.format("%s", e.getEvent().getMessage().getContentRaw().split("\\s+", 3)[2]);
            reason = reason.equals("") ? "No reason." : reason;

            //Count warned users.
            int count = 0;

            for(User u : mention) {
                if(u.isBot() || u.isFake())
                    continue;

                EmbedBuilder warn = new EmbedBuilder();
                warn.setColor(Color.red);
                warn.setAuthor("Warning from Server: " + e.getGuild().getName(), null, e.getGuild().getIconUrl());
                warn.addField("To:", names + e.getAuthor().getDiscriminator(), false);
                warn.addField("Moderator:", e.getMember().getEffectiveName() + e.getAuthor().getDiscriminator(), false);
                warn.addField("Reason:", reason, false);
                warn.setThumbnail(e.getGuild().getIconUrl());
                warn.setFooter("Sent by " + e.getMember().getEffectiveName(), e.getAuthor().getEffectiveAvatarUrl());
                warn.setTimestamp(Instant.now());
                e.getChannel().sendMessage(warn.build()).queue();

                count ++;
            }
        }
        else if(args.size()>0 && !"-h".equals(args.get(0)))
            e.getChannel().sendMessage(Emoji.ERROR + " This command is for server owner or\n"
                    + "members with `Manage Server` or `Manage Channel` Permissions only.").queue();
    }


    @Override
    public String getName() {
        return "warn";
    }

    @Override
    public String getHelp() {
        return "This command is for banning members.\n"
                + "Command Usage: `"+ Config.get("prefix") +"warn`\n"
                + "Parameter: `-h | @Member(s) [Reason]\n`"
                + "@Member(s) [Reason]: Mention members to warn, then give a reason.\n";
    }
}
