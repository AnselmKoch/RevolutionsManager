package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.file.savefiles.CommandFile;
import me.anselm.ello.Main;

import java.io.*;

public class FileSaver {


    private FileOutputStream fileOutputStream;
    private BufferedWriter bufferedWriter;
    private FileReader fileReader;
    private SaveFile saveFile;

    public FileSaver(SaveFile saveFile) throws FileNotFoundException {
        this.saveFile = saveFile;
        this.fileReader = saveFile.getFileReader();
    }

    public void writeString(String string) throws IOException {
        fileOutputStream = new FileOutputStream(saveFile, true);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(string);
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileReader.readFile(saveFile);
        bufferedWriter.close();
    }

    public void saveString(String string) throws IOException {
        fileOutputStream = new FileOutputStream(saveFile, true);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        if(!saveFile.getFileReader().containsString(string)) {

            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
       saveFile.setContent(fileReader.readFile(getSaveFile()));
    }
    public void saveCommand(Command command) throws IOException {
        fileOutputStream = new FileOutputStream(saveFile, true);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        SaveFile saveFile = MainClass.fileManager.getSaveFileFromName("command");
        if(command.getTextChannel() != null) {
            if(!saveFile.getFileReader().containsString(command.getCmdName())) {

                bufferedWriter.write(command.getCmdName() + " " + command.getTextChannel().getId());
                bufferedWriter.newLine();
                bufferedWriter.close();

            }else{
                System.out.println("Test123");
            }
        }
        fileReader.readFile(MainClass.fileManager.getSaveFileFromName("command"));
    }

    public SaveFile getSaveFile() {
        return saveFile;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}
