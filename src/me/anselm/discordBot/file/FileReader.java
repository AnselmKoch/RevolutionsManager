package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileReader {

    private ArrayList<String> fileStrings;
    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;
    private SaveFile file;

    public FileReader(SaveFile file) throws IOException {
        this.file = file;
        fileStrings = new ArrayList<>();
    }

    public void deleteString(String delete) {
    }
    public boolean containsString(String stringSearch) {
        for(String string : fileStrings) {
            if(string != null) {
                if(string.equalsIgnoreCase(stringSearch)) {
                    return true;
                }
            }
        }
        return false;
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

    public ArrayList<String> readFile(SaveFile saveFile) throws IOException {
        fileInputStream = new FileInputStream(file);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        ArrayList<String> arrayList = new ArrayList<>();
        String nextLine = bufferedReader.readLine();
        while(nextLine != null) {
            arrayList.add(nextLine);
            nextLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        return arrayList;
    }

    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }
}
