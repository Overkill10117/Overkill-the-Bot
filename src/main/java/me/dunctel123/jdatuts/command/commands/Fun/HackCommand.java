package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class HackCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();

        if (mentionedMembers.isEmpty()) {
            ctx.getChannel().sendMessage("Missing mentioned members").queue();
            return;
        }

        ctx.getChannel().sendMessage("Hack101 using HPS 2.0, PHS!12, and LMS473 activated...\n").queue();

        int hackCount = 0;

        while (hackCount <= 10) {
            ctx.getChannel().sendMessage("Hacking the DNS, Server Encryption, and the CPS...\n" +
                    "Step " + hackCount + " of 10 complete").queue();
            hackCount++;
        }

        hackCount = 0;

        while (hackCount <= 4) {
            ctx.getChannel().sendMessage("Covering the tracks in the DNS, Server Encryption, and the CPS...\n" +
                    "Step " + (hackCount*1000) + " of 40,000 complete\n").queue();
            hackCount++;
        }

        ctx.getChannel().sendMessage("Account created (using HPS 2.0) ....\n" +
                "User name = hack101\n" +
                "Password = 4710923").queue();

        ctx.getChannel().sendMessage("See the targeted computer's stats (BETA) .... Chosen!!!").queue();
        System.out.println("Beginning hack...");
        hackCount = 0;

        while (hackCount <= 2) {
            ctx.getChannel().sendMessage("Locating the computer of " + mentionedMembers.get(0).getEffectiveName() + " (using LMS473) . . .\n" +
                    "Step " + hackCount*5 + " of 10 complete\n").queue();
            hackCount++;
        }

        ctx.getChannel().sendMessage("Location found...").queue();
        ctx.getChannel().sendMessage("Philippines, Quezon City").queue();
        ctx.getChannel().sendMessage("Latest message sent is.....").queue();

        double meh = Math.random()*10;

        if (meh== 1 || meh == 2) {
            ctx.getChannel().sendMessage("I love you").queue();
        } else if (meh==3|| meh == 4) {
            ctx.getChannel().sendMessage("I am Gay").queue();
        } else if (meh==5||meh==6) {
            ctx.getChannel().sendMessage("Math is the best subject").queue();
        } else if (meh==7||meh==8) {
            ctx.getChannel().sendMessage("You are a Gay").queue();
        } else {
            ctx.getChannel().sendMessage("@everyone I am gay").queue();
        }

        ctx.getChannel().sendMessage(mentionedMembers.get(0).getEffectiveName() + " sent the message to.....").queue();

        meh = Math.random()*10;

        switch ((int) meh) {
            case 1: case 2:
                ctx.getChannel().sendMessage("Isabella").queue();
                return;
            case 3: case 4:
                ctx.getChannel().sendMessage("William").queue();
                return;
            case 5: case 6:
                ctx.getChannel().sendMessage("A total gay").queue();
                return;
            case 7: case 8:
                ctx.getChannel().sendMessage("John").queue();
                return;
            default:
                ctx.getChannel().sendMessage("Sean").queue();
        }
    }


    @Override
    public String getName() {
        return "hack";
    }

    @Override
    public String getHelp() {
        return "hack [person]";
    }
}