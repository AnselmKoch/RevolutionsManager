package me.anselm.discordBot.listener;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.FileManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReceiveCommandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        long messageID = event.getMessageIdLong();
        Bot.guild = event.getGuild();
        Message message1 = event.getMessage();

            String message = event.getMessage().getContentRaw();
            TextChannel textChannel = event.getTextChannel();

            if (message.startsWith(Bot.prefix)) {

                message = message.replace(Bot.prefix, "");
                String commandString = message.split(" ")[0];
                Command command = CommandManager.getCommandFromName(commandString);


                if(commandString.equalsIgnoreCase("delete")) {

                    message = message.replace(commandString + " ", "");
                    try {
                        command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(commandString.equalsIgnoreCase("avatar")) {
                    try{
                        command.execute(event.getMember(), message1, textChannel, event.getGuild());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(commandString.equalsIgnoreCase("bind")) {

                    message = message.replace(commandString + " ", "");
                    try {
                        command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(commandString.equalsIgnoreCase("unbind")) {
                    message = message.replace(commandString + " ", "");
                    try {
                        command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    if((command != null) && (command.getTextChannel() != null) && (command.getTextChannel().getId().equalsIgnoreCase(event.getTextChannel().getId()))) {

                        message = message.replace(commandString + " ", "");
                        try {
                            command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            } else {
                if (textChannel.getId().equals("843894124143050792") && (!event.getMember().getEffectiveName().equalsIgnoreCase("Revolution Manager")) && (!event.getMember().getId().equalsIgnoreCase("716390085896962058"))) {
                    ArrayList<String> arrayList = Bot.fileManager.getSaveFileFromName("pokespam").getContent();

                    textChannel.sendMessage(arrayList.get(new Random().nextInt(arrayList.size()))).queue();
                }
            }

            boolean shouldDelete = false;
            for(Command command : CommandManager.getCommands()) {
                if(command.getTextChannel() != null) {
                    if (command.getTextChannel().getId().equalsIgnoreCase(textChannel.getId())) {
                        if(!event.getMember().getId().equalsIgnoreCase("716390085896962058")) {
                            shouldDelete = true;
                            if((textChannel.getId().equalsIgnoreCase("847067890863833136")) && (!event.getMember().getId().equalsIgnoreCase("843898180126244916"))) {
                                shouldDelete = true;
                            }
                        }
                    }
                }
            }

            if(message.startsWith("Hier deine Pappe...")) {
                shouldDelete = false;
            }
            if(textChannel.getId().equalsIgnoreCase("847067890863833136")) {
                shouldDelete = false;
            }
            if(message.startsWith("Zitat")) {
                shouldDelete = false;
            }

            if(message.startsWith("Mathe mois:")) {
                shouldDelete = false;
            }

            if(shouldDelete){
                textChannel.deleteMessageById(event.getMessageId()).queueAfter(3, TimeUnit.SECONDS);
            }

    }

}

