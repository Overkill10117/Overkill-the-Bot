package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;

public class TableCatchCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getChannel().sendMessage("(╯°□°）╯︵ ┻━┻ノ( ゜-゜ノ)").queue();
    }

    @Override
    public String getName() {
        return "tc";
    }

    @Override
    public String getHelp() {
        return "table catch";
    }
}
