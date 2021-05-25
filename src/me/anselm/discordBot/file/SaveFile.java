package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.file.savefiles.CommandFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public abstract class SaveFile extends File implements Cloneable{

    private String name;
    private final String pathname;
    private final FileReader fileReader;
    private final FileSaver fileSaver;
    private ArrayList<String> content;

    public SaveFile(String pathname, String name) throws IOException {
        super(pathname);
        this.pathname = pathname;
        this.name = name;
        if(!this.exists()) {
            this.createNewFile();
        }
        this.fileReader = new FileReader(this);
        this.fileSaver = new FileSaver(this);
        content = getFileReader().readFile(this);
        this.setupContent();
    }

    public void deleteCommand(String line) throws IOException {
        SaveFile tempFile = new CommandFile("temp.dc", "command");
        SaveFile currentSaveFile = this;
        for(String string : currentSaveFile.content) {
            System.out.println(string + " string");
            if (!string.equalsIgnoreCase(line)) {
                tempFile.fileSaver.writeString(string);
                System.out.println(string + " geschriebem");
            }
        }
        System.gc();
        currentSaveFile.delete();
        tempFile.renameTo(currentSaveFile);
        MainClass.fileManager.removeAndAddSaveFile(tempFile);
    }
    public String getName() {
        return this.name;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    protected abstract void setupContent();

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> strings) {
        this.content = strings;
    }

    public void setName(String string) {
        this.name = name;
    }

    public FileSaver getFileSaver() {
        return fileSaver;
    }

    public String getPathname() {
        return pathname;
    }
}
