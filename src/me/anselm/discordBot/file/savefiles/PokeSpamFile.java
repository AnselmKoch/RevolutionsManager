package me.anselm.discordBot.file.savefiles;

import me.anselm.discordBot.file.SaveFile;

import java.io.IOException;

public class PokeSpamFile extends SaveFile {

    public PokeSpamFile(String pathname, String name) throws IOException {
        super(pathname, name);
    }

    @Override
    protected void setupContent() {

    }
}
