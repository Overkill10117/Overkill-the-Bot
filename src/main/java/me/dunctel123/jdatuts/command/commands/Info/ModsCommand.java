package me.dunctel123.jdatuts.command.commands.Info;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.dunctel123.jdatuts.command.commands.Emoji;
import me.dunctel123.jdatuts.command.commands.UtilBot;
import me.dunctel123.jdatuts.command.commands.UtilNum;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModsCommand implements ICommand {
    @Override
    public void handle(CommandContext e) {
        List<Member> mods = getMods(e.getGuild());
        StringBuilder output = new StringBuilder("Mods in **").append(e.getGuild().getName()).append("**\n");
        String Own = String.format("%s (Owner)", Objects.requireNonNull(e.getGuild().getOwner()).getUser().getAsTag());
        String laaa = String.format("%s (Admin)", e.getGuild().getMembers().stream().filter(member -> member.getPermissions().contains(Permission.ADMINISTRATOR)));

        /* Iterate through mod list */
        for (Member mem : mods) {
            output.append(UtilBot.getStatusEmoji(mem.getOnlineStatus()))
                    .append(mem.getUser().getName()).append("#").append(mem.getUser().getDiscriminator())
                    .append(mem.isOwner() ? " (Owner)" : "")
                    .append(e.getMember().hasPermission(Permission.ADMINISTRATOR) && !mem.isOwner() ? " (Admin)" : "").append("\n");
        }
        String message = String.format(String.valueOf(output), Own , laaa);
        e.getChannel().sendMessage(message).queue();
    }
    private List<Member> getMods(Guild guild) {
        return guild.getMemberCache().stream()
                    .filter(member -> !member.getUser().isBot())
                    .filter(member ->
                            (member.getPermissions().contains(Permission.ADMINISTRATOR)))
                    .collect(Collectors.toList());

    }

    @Override
    public String getName() {
        return "mods";
    }

    @Override
    public String getHelp() {
        return "This command is for getting a list of mods in this server."
                + "Command Usage: `,mods`\n";
    }
}
