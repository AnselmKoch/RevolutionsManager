package me.anselm.discordBot.listener;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.commands.CommandManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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

                if(commandString.equalsIgnoreCase("add")) {

                    message = message.replace(commandString + " ", "");
                    command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                }else if(commandString.equalsIgnoreCase("removecmd")) {
                    message = message.replace(commandString + " ", "");
                    command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());
                }else{
                    if((command != null) && (command.getTextChannel() != null) && (command.getTextChannel().getId().equalsIgnoreCase(event.getTextChannel().getId()))) {

                        System.out.println(command.getTextChannel().getId());
                        message = message.replace(commandString + " ", "");
                        command.execute(event.getMember(), message, event.getTextChannel(), event.getGuild());

                    }
                }

                event.getTextChannel().deleteMessageById(event.getMessageId()).queueAfter(10, TimeUnit.SECONDS);

            } else {
                if (textChannel.getId().equals("843894124143050792")) {
                    String[] words = {"Hallo", "Ich mag Äpfel", "Danke du auch", "Ich bin am ballen jeden tag weil ich ein baller bin", "no touching, i find it disgusting",
                            "müsli mag ich am meisten als Frühstück", "Da gebe ich dir Recht!", "Das sehe ich auch so!", "Naja irgendwie sehe ich das anders", "Was laberst du?",
                            "Mach dich mal lieber aus meiner Leitung du birne", "Ich schmeiß dir so die Brügel naus", "Traut euch, kommt zu mir!", "1+1 ist 4", "Hab ein IQ von ca. 319",
                            "Nur der gefickte ist schwul", "Manchmal ist weniger echt weniger", "Manchmal ist mehr tatsache mehr", "looool als ob hahaha laber mal nicht",
                            "Wer schwankt hat mehr vom Weg!", "Hasch du Überhaupt gelernt?", "Das trifft mich jetzt so hart, wie ein Wackelpudding", "Woooow a Kastanie. Hmmmmm unverzüglich"
                            , "Du bist bestimmt Yasuo main...", "Ich bin a waschechter meddler", "Spielt eigentlich jemand Killing floor?", "Ratschläge sind auch nur Schläge.",
                            "Im Herzen sind wir alle albaner", "Lieber im stuhl einschlafen als im schlaf einstuhlen", "Lieber arm dran als arm im darm", "Ich bin nicht faul, ich bin im Energiesparmodus",
                            "Draaaaain gaaaaaaaang", "Shououts to Ecco, he pulled up in a rover, drain my life", "Wer zuletzt lacht, hat es nicht eher verstanden",
                            "Menschen mit einem i im namen sind durschnittlich nicht gut in LoL", "Moneyboy MACHT gute Musik", "Pferdeäpfel sind die schlechtesten Äpfel",
                            "Ich könnte das machen aber ich will es nur nicht", "Also ich bin eigentlich Pazifist", "Oh shes sweet but a psycho, a little bit psycho", "Frag nicht was für Saft, Orangensaft",
                            "PokeTwo ist nicht cool!"};
                    textChannel.sendMessage(words[new Random().nextInt(words.length)]).queue();
                }
            }
            if((event.getMember() != null) && (event.getMember().getEffectiveName().equalsIgnoreCase("Revolution Manager"))) {
                textChannel.deleteMessageById(event.getMessageId()).queueAfter(10, TimeUnit.SECONDS);
            }
        }
    }
