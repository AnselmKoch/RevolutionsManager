package me.anselm.discordBot.listener;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.games.game.TikTakToe;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class TikTakToeListener extends ListenerAdapter {

    public static Message tikTakToeMessage;
    private TikTakToe tikTakToe = TikTakToe.instance;
    public void onMessageReceived(MessageReceivedEvent event) {
        Member sender = event.getMember();

        if (event.getTextChannel().getId().equals("847067890863833136") && (TikTakToe.running) && (!event.getMember().getId().equals(Bot.jdaID))) {
            if (TikTakToe.players[0] == null) {
                TikTakToe.players[0] = event.getMember();
                event.getTextChannel().sendMessage("Du bist jetzt der 1. Spieler! <@" + event.getMember().getId() + ">").queue();
                return;
            } else if (TikTakToe.players[1] == null) {
                    TikTakToe.players[1] = event.getMember();
                    event.getTextChannel().sendMessage("Du bist jetzt der 2. Spieler! <@" + event.getMember().getId() + ">").queue();
                    TikTakToe.instance.setMemberOnTurn(TikTakToe.players[0]);
                    return;
            }

            try{
                int tile = Integer.parseInt(event.getMessage().getContentRaw());
                if(tile >= 1 && tile <= 9) {
                    TikTakToe.instance.processTurn(tile, sender);
                }
            }catch (NumberFormatException numberFormatException) {
            }
        }
    }
}
