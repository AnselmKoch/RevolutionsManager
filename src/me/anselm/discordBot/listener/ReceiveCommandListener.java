package me.anselm.discordBot.listener;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReceiveCommandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) throws NullPointerException {
        long messageID = event.getMessageIdLong();
        MainClass.guild = event.getGuild();

            String message = event.getMessage().getContentRaw();
            TextChannel textChannel = event.getTextChannel();

            if (message.startsWith(MainClass.prefix)) {

                message = message.replace(MainClass.prefix, "");
                String commandString = message.split(" ")[0];
                Command command = CommandManager.getCommandFromName(commandString);

                if(commandString.equalsIgnoreCase("bind")) {

                    message = message.replace(commandString + " ", "");
                    command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                }else if(commandString.equalsIgnoreCase("unbind")) {
                    message = message.replace(commandString + " ", "");
                    command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                }else{
                    if((command != null) && (command.getTextChannel() != null) && (command.getTextChannel().getId().equalsIgnoreCase(event.getTextChannel().getId()))) {

                        message = message.replace(commandString + " ", "");
                        command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());

                    }
                }

            } else {
                if (textChannel.getId().equals("843894124143050792") && (!event.getMember().getEffectiveName().equalsIgnoreCase("Revolution Manager")) && (!event.getMember().getId().equalsIgnoreCase("716390085896962058"))) {
                    ArrayList<String> arrayList = MainClass.fileManager.getSaveFileFromName("pokespam").getContent();

                    textChannel.sendMessage(arrayList.get(new Random().nextInt(arrayList.size()))).queue();
                }
            }

            boolean shouldDelete = false;
            for(Command command : CommandManager.getCommands()) {
                if(command.getTextChannel() != null) {
                    if (command.getTextChannel().getId().equalsIgnoreCase(textChannel.getId())) {
                        if(!event.getMember().getId().equalsIgnoreCase("716390085896962058")) {
                            shouldDelete = true;
                        }
                    }
                }
            }

            if(message.startsWith("Hier deine Pappe...")) {
                shouldDelete = false;
            }
            if(shouldDelete){
                textChannel.deleteMessageById(event.getMessageId()).queueAfter(3, TimeUnit.SECONDS);
            }

    }

}

