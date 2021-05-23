package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;

import java.io.*;
import java.util.ArrayList;

public class FileReader {

    private ArrayList<String> fileStrings;
    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;
    private File file = new File(MainClass.commandFilePath);

    public FileReader() throws IOException {
        fileStrings = new ArrayList<>();
        fileInputStream = new FileInputStream(file);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        readFile();
        setUpCommands();
    }

    public boolean containsCommand(Command command) {
        for(String string: fileStrings) {
            if(string != null) {
                String commandToFind = string.split(" ")[0];
                if(commandToFind.equalsIgnoreCase(command.getCmdName())) {
                    return true;
                }
            }
        }
        return false;
    }


    public void setUpCommands() {
        for(String string : fileStrings) {
            if(string != null) {
                String[] commands = string.split(" ");
                System.out.println(commands[0]);
                System.out.println(commands[1]);
                Command command = CommandManager.getCommandFromName(commands[0]);
                System.out.println(command.getCmdName());
                command.setTextChannel(CommandManager.getTextChannelFromID(commands[1]));
                System.out.println(command.getTextChannel());
            }
        }
    }
    public Command getCommandInFile(Command command) {
        for(String string : fileStrings) {
            if(string != null) {
                String commandToFind = string.split(" ")[0];
                if(command.getCmdName().equalsIgnoreCase(commandToFind)) {
                    return CommandManager.getCommandFromName(commandToFind);
                }
            }
        }
        return null;
    }

    public void readFile() throws IOException {
        String nextLine = bufferedReader.readLine();
        while(nextLine != null) {
            fileStrings.add(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }
}
