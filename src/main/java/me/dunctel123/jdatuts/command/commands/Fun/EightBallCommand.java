package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;

public class EightBallCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage("Missing question").queue();
            return;
        }

        if (ctx.getArgs().contains("shion") && ctx.getArgs().contains("gay")) {
            ctx.getChannel().sendMessage("`Absolutely not`").queue();
            return;
        }

        if (ctx.getArgs().contains("shion") && ctx.getArgs().contains("gae")) {
            ctx.getChannel().sendMessage("`Absolutely not`").queue();
            return;
        }

        if (ctx.getArgs().contains("shion") && ctx.getArgs().contains("smart")) {
            ctx.getChannel().sendMessage("`Absolutely`").queue();
            return;
        }

        if (ctx.getArgs().contains("shion") && ctx.getArgs().contains("very smart")) {
            ctx.getChannel().sendMessage("`Absolutely`").queue();
            return;
        }

        double meh = Math.random()*10;

        switch ((int) meh) {
            case 1:
                ctx.getChannel().sendMessage("`Nah`").queue();
                return;
            case 2:
                ctx.getChannel().sendMessage("`Yes`").queue();
                return;
            case 3:
                ctx.getChannel().sendMessage("`No one knows`").queue();
                return;
            case 4:
                ctx.getChannel().sendMessage("`Absolutely`").queue();
                return;
            case 5:
                ctx.getChannel().sendMessage("`You're seriously asking that to me`").queue();
                return;
            case 6:
                ctx.getChannel().sendMessage("`It is unknown`").queue();
                return;
            case 7:
                ctx.getChannel().sendMessage("`Sure....`").queue();
                return;
            case 8:
                ctx.getChannel().sendMessage("`No`").queue();
                return;
            default:
                ctx.getChannel().sendMessage("`Maybe`").queue();
        }
    }

    @Override
    public String getName() {
        return "8ball";
    }

    @Override
    public String getHelp() {
        return "8ball [question]";
    }
}
