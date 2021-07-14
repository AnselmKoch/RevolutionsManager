package me.anselm.discordBot.file.savefiles;

import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.SaveFile;

import java.io.IOException;
import java.util.ArrayList;

public class CommandFile extends SaveFile {


    public CommandFile(String pathname, String name) throws IOException {
        super(pathname, name);
    }


    @Override
    protected void setupContent() {
        for(String string : getContent()) {
            if(string != null) {
                String[] commands = string.split(" ");
                Command command = CommandManager.getCommandFromName(commands[0]);
                command.setTextChannel(CommandManager.getTextChannelFromID(commands[1]));
            }
        }
    }

}
