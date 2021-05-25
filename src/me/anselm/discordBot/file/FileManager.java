package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.file.savefiles.CommandFile;
import me.anselm.discordBot.file.savefiles.PokeSpamFile;
import me.anselm.discordBot.file.savefiles.RoleFiles;

import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private ArrayList<SaveFile> saveFiles;

    public FileManager() throws IOException {
        saveFiles = new ArrayList<>();
        saveFiles.add(new CommandFile(MainClass.commandFilePath, "command"));
        saveFiles.add(new PokeSpamFile(MainClass.pokeFilePath, "pokespam"));
        saveFiles.add(new RoleFiles(MainClass.roleFilePath, "roles"));
    }

    public void removeAndAddSaveFile(SaveFile saveFile) {
        for(SaveFile saveFile1 : saveFiles) {
            if(saveFile.getName().equalsIgnoreCase(saveFile1.getName())) {
                saveFiles.remove(saveFile1);
                saveFiles.add(saveFile);
            }
        }
    }
    public SaveFile getSaveFileFromName(String name) {
        for (SaveFile saveFile : saveFiles) {
            if(name.equalsIgnoreCase(saveFile.getName())) {
                return saveFile;
            }
        }
        return null;
    }
}
