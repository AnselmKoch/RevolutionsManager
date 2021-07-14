package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeleteCommand extends Command {

    public DeleteCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {
        Thread thread = new Thread() {
          @Override
          public void run() {
              System.out.println("thread running");
              for(Message message1 : textChannel.getIterableHistory()) {
                  message1.delete().queue();
                  try {
                      sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
        thread.run();
        thread.stop();
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
