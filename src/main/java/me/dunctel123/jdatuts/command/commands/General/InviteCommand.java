package me.dunctel123.jdatuts.command.commands.General;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;

public class InviteCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getChannel().sendMessage("https://discord.com/api/oauth2/authorize?client_id=775257176050237480&permissions=8&scope=bot").queue();
    }

    @Override
    public String getName() {
        return "invite";
    }

    @Override
    public String getHelp() {
        return "invite";
    }
}
