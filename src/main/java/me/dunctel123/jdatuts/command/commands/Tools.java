package me.dunctel123.jdatuts.command.commands;

import me.dunctel123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

public class Tools {
    public static void wrongUsage(TextChannel tc, ICommand c) {
        tc.sendMessage("Wrong Command Usage!\n" + c.getHelp()).queue();
    }
}
