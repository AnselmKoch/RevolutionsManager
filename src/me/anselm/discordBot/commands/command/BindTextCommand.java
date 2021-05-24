package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.SaveFile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;

public class BindTextCommand extends Command {

    public BindTextCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        String[] args = message.split(" ");
        if(args.length == 1) {
            Command commandToBind = CommandManager.getCommandFromName(message);
            SaveFile saveFile = MainClass.fileManager.getSaveFileFromName("command");
            if(commandToBind.getTextChannel() == null) {
                commandToBind.setTextChannel(textChannel);
                try {
                    saveFile.getFileSaver().saveCommand(commandToBind);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textChannel.sendMessage("Erolfgreich den Befehl " + commandToBind.getCmdName() + " an diesen Textchannel gebunden...").queue();
            }else if(CommandManager.getCommandFromTextCH(textChannel) != null){
                textChannel.sendMessage("An diesen Channel ist bereits der " + CommandManager.getCommandFromTextCH(textChannel).getCmdName() + " command gebunden...").queue();
            }else{
                textChannel.sendMessage("An dem Command '" + commandToBind.getCmdName() + "' ist bereits der Channel '" + CommandManager.getTextChannelFromID(commandToBind.getTextChannel().getId()).getName() + "' gebunden...").queue();
            }
        }
    }
}
