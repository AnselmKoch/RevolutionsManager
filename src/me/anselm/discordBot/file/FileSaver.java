package me.anselm.discordBot.file;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;

import java.io.*;

public class FileSaver {


    public File file;
    private FileOutputStream fileOutputStream;
    private BufferedWriter bufferedWriter;
    private FileReader fileReader;

    public FileSaver() throws IOException {
        file = new File(MainClass.commandFilePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created file at " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to create Command save file...");
                e.printStackTrace();
            }
        }
        fileOutputStream = new FileOutputStream(file, true);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        fileReader = new FileReader();
    }

    public void saveCommand(Command command) throws IOException {
        if(command.getTextChannel() != null) {
            if(!fileReader.containsCommand(command)) {
                try {
                    bufferedWriter.write(command.getCmdName() + " " + command.getTextChannel().getId());
                } catch (IOException e) {
                    System.out.println("Failed to save command...");
                    e.printStackTrace();
                }
                try {
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.println("Failed to write a new line...");
                    e.printStackTrace();
                }
            }else{
                System.out.println("Test123");
            }
        }
        fileReader.readFile();
    }
}
