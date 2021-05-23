package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class RemoveTextCommand extends Command {

    public RemoveTextCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        String[] args = message.split(" ");
        if(args.length == 1) {
            Command commandToRemove = CommandManager.getCommandFromTextCH(textChannel);
            if(commandToRemove != null) {
                commandToRemove.setTextChannel(null);
                textChannel.sendMessage("Erolfgreich den Befehl " + commandToRemove.getCmdName() + " von diesem Textchannel entbunden...").queue();
            }else{
                textChannel.sendMessage("An diesen Textchannel ist noch kein Command gebunden...").queue();
            }
        }
    }
}
