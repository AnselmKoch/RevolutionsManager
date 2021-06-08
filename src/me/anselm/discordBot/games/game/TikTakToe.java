package me.anselm.discordBot.games.game;

import me.anselm.discordBot.commands.command.TikTakToeCommand;
import me.anselm.discordBot.games.Game;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TikTakToe extends Game{

    public static TikTakToe instance;
    private BufferedImage bufferedImage;
    public static Member[] players = new Member[2];
    private boolean isMemberOneTurn;
    public static boolean running;
    private static BufferedImage userImage;
    private static Graphics graphics;
    private int[] values;
    private int[] fields;
    private Member gameWinner;

    public TikTakToe() {
        instance = this;
    }

    @Override
    public void start() {
        running = true;
        players = new Member[2];
        isMemberOneTurn = true;
        bufferedImage = createImage();
        values = new int[]{0,1,2,3,4,5,6,7,8};
        fields =  new int[9];
        isMemberOneTurn = true;
    }

    public BufferedImage createImage() {
        BufferedImage bufferedImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
        if(bufferedImage.getGraphics() == null) {
            bufferedImage.createGraphics();
        }
        graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, 22));

        graphics.setColor(Color.white);
        bufferedImage.getGraphics().fillRect(0,0,500,500);
        graphics.setColor(Color.RED);
        int field = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                field++;
                graphics.drawString(String.valueOf(field),((500/3) * j+1) + (500/3/2), ((500/3) * i+1) + (500/3/2));
            }
        }
        graphics.setColor(Color.red);
        for(int i = 0; i < 500; i++) {
            if((i % (500 / 3 ) == 0)) {
                for(int j = -2; j < 2; j++) {
                    graphics.drawLine(0, i+j,  500, i+j);
                }
            }
        }
        for(int i = 0; i < 500; i++) {
            if ((i % (500 / 3) == 0)) {
                for (int j = -2; j < 2; j++) {
                    graphics.drawLine(i + j, 0, i + j, 500);
                }
            }
        }
        return bufferedImage;
    }

    public static BufferedImage drawOnTile(int tile, Member sender) throws IOException {
        URL userURL;
        userURL = new URL(sender.getUser().getAvatarUrl());
        userImage = ImageIO.read(userURL);
        userImage = resize(userImage, 500 / 3 - 4, 500 / 3 - 4);
        File file = new File("testuser.png");
        ImageIO.write(userImage, "png", file);

        switch (tile) {
            case 1:   graphics.drawImage(userImage, 2,2, null);break;
            case 2: graphics.drawImage(userImage, 500/3 + 2, 2, null);break;
            case 3: graphics.drawImage(userImage,500/3*2 + 2, 2, null);break;
            case 4:   graphics.drawImage(userImage, 2,500/3+2, null);break;
            case 5: graphics.drawImage(userImage, 500/3 + 2, 500/3+2, null);break;
            case 6: graphics.drawImage(userImage,500/3*2 + 2, 500/3+2, null);break;
            case 7:   graphics.drawImage(userImage, 2,500/3*2+2, null);break;
            case 8: graphics.drawImage(userImage, 500/3 + 2, 500/3*2+2, null);break;
            case 9: graphics.drawImage(userImage,500/3*2 + 2, 500/3*2+2, null);break;
        }

        return instance.bufferedImage;
    }

    //graphics.drawImage(userImage, 2,2, null);
    // graphics.drawImage(userImage, 500/3 + 2,500/3 + 2, null);

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public void endGame() {
        players[0] = null;
        players[1] = null;
        running = false;
    }
    public boolean checkWinner() {
       if(fields[0] * fields[1] * fields[2] == 1) {
           gameWinner = players[0];
           return true;
       }else if(fields[0] * fields[1] * fields[2] == 6) {
           gameWinner = players[1];
           return true;
       }
        if(fields[6] * fields[7] * fields[8] == 1) {
            gameWinner = players[0];
            return true;
        }else if(fields[6] * fields[7] * fields[8] == 6) {
            gameWinner = players[1];
            return true;
        }
        if(fields[0] * fields[4] * fields[8] == 1) {
            gameWinner = players[0];
            return true;
        }else if(fields[0] * fields[4] * fields[8] == 6) {
            gameWinner = players[1];
            return true;
        }
        if(fields[0] * fields[3] * fields[6] == 1) {
            gameWinner = players[0];
            return true;
        }else if(fields[0] * fields[3] * fields[6] == 6) {
            gameWinner = players[1];
            return true;
        }
        if(fields[2] * fields[5] * fields[8] == 1) {
            gameWinner = players[0];
            return true;
        }else if(fields[2] * fields[5] * fields[8] == 6) {
            gameWinner = players[1];
            return true;
        }
        if(fields[2] * fields[4] * fields[6] == 1) {
            gameWinner = players[0];
            return true;
        }else if(fields[2] * fields[4] * fields[6] == 6) {
            gameWinner = players[1];
            return true;
        }


        return false;
    }


    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public void setFieldBool(int index, int value) {
        this.fields[index] = value;
    }

    public void setValue(int index, int value) {
        this.values[index] = value;
    }

    public boolean isOccupied(int index) {
        if(fields[index] == 1 || fields[index] == 2) {
            return true;
        }else{
            return false;
        }
    }
    public int getField(int index) {
        return fields[index];
    }
    public int[] getFields() {
        return fields;
    }

    public int[] getValues() {
        return values;
    }

    public boolean isMemberOneTurn() {
        return isMemberOneTurn;
    }

    public void setMemberOneTurn(boolean memberOneTurn) {
        isMemberOneTurn = memberOneTurn;
    }

    public Member getGameWinner() {
        return gameWinner;
    }
}
