package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.SaveFile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;

public class UnbindTextCommand extends Command {

    public UnbindTextCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        String[] args = message.split(" ");
        if(args.length == 1) {
            Command commandToRemove = CommandManager.getCommandFromName(args[0]);
            SaveFile saveFile = Bot.fileManager.getSaveFileFromName("command");
            try {
                saveFile.deleteCommand(commandToRemove.getCmdName() + " " + commandToRemove.getTextChannel().getId());
                commandToRemove.setTextChannel(null);
                textChannel.sendMessage("Der Command wurde erfolgreich entbunden...").queue();
            } catch (Exception e) {
                textChannel.sendMessage("Der Command konnte nicht entbunden werden...").queue();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
