package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.file.SaveFile;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;

public class AddSpamCommand extends Command {

    public AddSpamCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        if(message != null) {
            SaveFile saveFile = Bot.fileManager.getSaveFileFromName("pokespam");
            try {
                saveFile.getFileSaver().saveString(message);
                textChannel.sendMessage("Die Nachricht wurde erfolgreich gespeichert und wird in Zukunft mit gespamt werden...").queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
