package me.anselm.discordBot.listener;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.games.game.TikTakToe;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.ReceivedMessage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;




public class TikTakToeListener extends ListenerAdapter {

    public static Message tikTakToeMessage;
    private TikTakToe tikTakToe = TikTakToe.instance;
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getTextChannel().getId().equals("847067890863833136") && (TikTakToe.running) && (!event.getMember().getId().equals(MainClass.jdaID))) {
            if(TikTakToe.players[0] == null){
                TikTakToe.players[0] = event.getMember();
                event.getTextChannel().sendMessage("Du bist jetzt der 1. Spieler! <@" + event.getMember().getId()+">").queue();
                return;
            }else{
                if(TikTakToe.players[1] == null) {
                    TikTakToe.players[1] = event.getMember();
                    event.getTextChannel().sendMessage("Du bist jetzt der 2. Spieler! <@" + event.getMember().getId()+">").queue();
                }
            }
        }
        if(TikTakToe.instance.isMemberOneTurn() && event.getMember().getId().equalsIgnoreCase(TikTakToe.players[0].getId())) {
            if (TikTakToe.players[0] != null && TikTakToe.players[1] != null) {
                try {
                    int tile = Integer.parseInt(event.getMessage().getContentRaw());
                    if (tile >= 1 && tile <= 9) {
                        if (!TikTakToe.instance.isOccupied(tile - 1)) {
                            File file = new File("user.png");
                            if (TikTakToe.instance.isMemberOneTurn()) {
                                TikTakToe.instance.setMemberOneTurn(false);
                                ImageIO.write(TikTakToe.drawOnTile(tile, TikTakToe.players[0]), "png", file);
                                TikTakToe.instance.setFieldBool(tile - 1, 1);
                                System.out.println(TikTakToe.instance.getField(tile - 1));
                                event.getTextChannel().sendMessage("Spieler 2 ist an der Reihe...").addFile(file).queue();
                            } else {
                                TikTakToe.instance.setMemberOneTurn(true);
                                TikTakToe.instance.setFieldBool(tile - 1, 2);
                                ImageIO.write(TikTakToe.drawOnTile(tile, TikTakToe.players[1]), "png", file);
                                System.out.println(TikTakToe.instance.getField(tile - 1));
                                event.getTextChannel().sendMessage("Spieler 1 ist an der Reihe...").addFile(file).queue();
                            }
                        } else {
                            event.getTextChannel().sendMessage("Das Feld ist bereits besetzt...").queue();
                        }
                        if (TikTakToe.instance.checkWinner()) {
                            event.getTextChannel().sendMessage("Gewinner ist: " + TikTakToe.instance.getGameWinner().getEffectiveName() + "!").queue();
                            TikTakToe.instance.endGame();
                        }
                    }
                } catch (NumberFormatException | IOException numberFormatException) {

                }
            }
        }else if(!TikTakToe.instance.isMemberOneTurn() && event.getMember().getId().equalsIgnoreCase(TikTakToe.players[1].getId())) {
            if (TikTakToe.players[0] != null && TikTakToe.players[1] != null) {
                try {
                    int tile = Integer.parseInt(event.getMessage().getContentRaw());
                    if (tile >= 1 && tile <= 9) {
                        if (!TikTakToe.instance.isOccupied(tile - 1)) {
                            File file = new File("user.png");
                            if (TikTakToe.instance.isMemberOneTurn()) {
                                TikTakToe.instance.setMemberOneTurn(false);
                                ImageIO.write(TikTakToe.drawOnTile(tile, TikTakToe.players[0]), "png", file);
                                TikTakToe.instance.setFieldBool(tile - 1, 1);
                                System.out.println(TikTakToe.instance.getField(tile - 1));
                                event.getTextChannel().sendMessage("Spieler 2 ist an der Reihe...").addFile(file).queue();
                            } else {
                                TikTakToe.instance.setMemberOneTurn(true);
                                TikTakToe.instance.setFieldBool(tile - 1, 2);
                                ImageIO.write(TikTakToe.drawOnTile(tile, TikTakToe.players[1]), "png", file);
                                System.out.println(TikTakToe.instance.getField(tile - 1));
                                event.getTextChannel().sendMessage("Spieler 1 ist an der Reihe...").addFile(file).queue();
                            }
                        } else {
                            event.getTextChannel().sendMessage("Das Feld ist bereits besetzt...").queue();
                        }
                        if (TikTakToe.instance.checkWinner()) {
                            event.getTextChannel().sendMessage("Gewinner ist: " + TikTakToe.instance.getGameWinner().getEffectiveName() + "!").queue();
                            TikTakToe.instance.endGame();
                        }
                    }
                } catch (NumberFormatException | IOException numberFormatException) {

                }
            }
        }
    }
}
