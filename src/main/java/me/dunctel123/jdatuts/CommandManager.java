package me.dunctel123.jdatuts;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;
import me.dunctel123.jdatuts.command.commands.Admin.SetPrefixCommand;
import me.dunctel123.jdatuts.command.commands.Events.*;
import me.dunctel123.jdatuts.command.commands.Fun.*;
import me.dunctel123.jdatuts.command.commands.General.HelpCommand;
import me.dunctel123.jdatuts.command.commands.General.InviteCommand;
import me.dunctel123.jdatuts.command.commands.General.PingCommand;
import me.dunctel123.jdatuts.command.commands.Info.*;
import me.dunctel123.jdatuts.command.commands.Music.*;
import me.dunctel123.jdatuts.command.commands.Spamm.PrivateSpamCommand;
import me.dunctel123.jdatuts.command.commands.Spamm.SpamCommand;
import me.dunctel123.jdatuts.command.commands.Spamm.SpamCommand2;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager(EventWaiter waiter) {
        addCommand(new PingCommand());
        addCommand(new HelpCommand(this));
        addCommand(new WebHookCommand());
        addCommand(new JokeCommand());
        addCommand(new MemeCommand());
        addCommand(new HelloCommand());
        addCommand(new SpamCommand());
        addCommand(new SpamCommand2());
        addCommand(new EventWaiterCommand(waiter));
        addCommand(new AvatarCommand());
        addCommand(new CatCommand());
        addCommand(new DogCommand());
        addCommand(new GayCommand());
        addCommand(new LOLCOMMAND());
        addCommand(new HiCommand());
        addCommand(new SayCommand());
        addCommand(new DmCommand());
        addCommand(new HackCommand());
        addCommand(new TableCatchCommand());
        addCommand(new PrivateSpamCommand());
        addCommand(new EightBallCommand());
        addCommand(new ServerCommand());
        addCommand(new RandomCommand());
        addCommand(new EmojiCommand());
        addCommand(new InviteCommand());
        addCommand(new OraCommand());
        addCommand(new DeathNoteCommand());
        addCommand(new Screamcommand());
        addCommand(new InfoUserCommand());
        addCommand(new InfoServerCommand());
        addCommand(new ServerListCommand());
        addCommand(new PollCommand());
        addCommand(new AboutCommand());
        addCommand(new ReactionRolePrivateCommand());
        addCommand(new GiveawayCommand());
        addCommand(new UrbanCommand());
        addCommand(new WarnCommand());
        addCommand(new AnnounceCommand());
        addCommand(new KickCommand());
        addCommand(new ModsCommand());
        addCommand(new SetPrefixCommand());

        addCommand(new JoinCommand());
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new SkipCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new LeaveCommand());
        addCommand(new RepeatCommand());
        addCommand(new QueueCommand());

    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }

        }

        return null;
    }

    void handle(GuildMessageReceivedEvent event, String prefix) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix),  "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

}