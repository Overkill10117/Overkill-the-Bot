package me.dunctel123.jdatuts.command.commands.Events;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.Objects;

public class KickCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();

        if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
            channel.sendMessage(getHelp()).queue();
            return;
        }

        final Member target = message.getMentionedMembers().get(0);

        if (!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You are missing permission to kick this member").queue();
            return;
        }

        final Member selfMember = ctx.getSelfMember();

        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("I am missing permissions to kick that member").queue();
            return;
        }

        final String reason = String.join(" ", args.subList(1, args.size()));
        String dis = String.format("%s got kicked do not follow his example", target);
        final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setAuthor(ctx.getSelfUser().getAsTag(), null, ctx.getSelfUser().getEffectiveAvatarUrl())
                .setTitle("KICKED")
                .setDescription(dis)
                .addField("Reason:", reason, true)
                .setFooter("KICKED");

        ctx.getGuild()
                .kick(target, reason)
                .reason(reason)
                .queue(
                        (__) -> Objects.requireNonNull(ctx.getGuild().getSystemChannel()).sendMessage(embed.build()).queue(),
                        (error) -> channel.sendMessageFormat("Could not kick %s", error.getMessage()).queue()
                );
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getHelp() {
        return "Kick a member off the server.\n" +
                "Usage: `,kick <@user> <reason>`";
    }
}
