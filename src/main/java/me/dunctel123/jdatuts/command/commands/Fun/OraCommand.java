package me.dunctel123.jdatuts.command.commands.Fun;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.TimeUnit;

public class OraCommand implements ICommand {
    String Challenger;
    String victim;
    String Message;

    @Override
    public void handle(CommandContext ctx) {
        Challenger = ctx.getAuthor().getAsMention();
        TextChannel channel = ctx.getChannel();
        victim = ctx.getMessage().getMentionedMembers().get(0).getAsMention();
        Message = Challenger + " ora" + victim + "until" + victim + "dies " + Challenger + "wins already???";
        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("You did not mention who to fight with").queue();
            return;
        } else if (ctx.getArgs().equals(ctx.getAuthor())) {
            channel.sendMessageFormat("%s committed suicided", ctx.getAuthor().getAsMention()).queue();
            return;
        } else {
            ctx.getChannel().sendMessage("Fight begins in 3").queue((message -> {
                String content = "Fight begins in 2";
                message.editMessage(content).queueAfter(1, TimeUnit.SECONDS);
                content = "Fight begins in 1";
                message.editMessage(content).queueAfter(2, TimeUnit.SECONDS);
                content = "Fight begins in 0";
                message.editMessage(content).queueAfter(3, TimeUnit.SECONDS);
                int x = 4;
                message.editMessageFormat(randomAttack()).queueAfter(4, TimeUnit.SECONDS);
                message.editMessageFormat(randomAttack()).queueAfter(6, TimeUnit.SECONDS);
                message.editMessageFormat(randomAttack()).queueAfter(8, TimeUnit.SECONDS);
                message.editMessageFormat(randomAttack()).queueAfter(10, TimeUnit.SECONDS);
                message.editMessageFormat("%s WINS!!!", ctx.getAuthor()).queueAfter(12, TimeUnit.SECONDS);
                x += 4;
            }));
        }
    }
    public int random(int start, int finish) {
        return (int) (Math.random() * (finish - start + 1) + start);
    }

    public String randomAttack() {
        String message = Message;
        switch (random(1, 8)) {
            case 1:
                message = Challenger+" ora "+ victim +" until "+ victim +" dies ";
                break;
            case 2:
                message =  Challenger +" ora " + victim + "'s stomach until"+ victim +"vomits "
                        ;
                break;

            case 3:
                message = Challenger+" ora at " + victim + "'s face until " + victim + "'s face got broken "
                ;
                break;
            case 4:
                message = Challenger+ "ora ";
        }
        return message;

    }

    @Override
    public String getName() {
        return "ora";
    }

    @Override
    public String getHelp() {
        return "ora [mentioned user] many times!!!";
    }
}
