package me.anselm.discordBot.file.savefiles;

import me.anselm.discordBot.file.SaveFile;
import net.dv8tion.jda.api.entities.Role;

import java.io.IOException;
import java.util.ArrayList;

public class RoleFiles extends SaveFile {


    public RoleFiles(String pathname, String name) throws IOException {
        super(pathname, name);
    }

    @Override
    protected void setupContent() {
    }
}
