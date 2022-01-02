package fan.frozen.infinitycheck.chatInteractive;

import net.md_5.bungee.api.ChatColor;
import java.awt.Color;
import org.bukkit.command.CommandSender;

public class ChatInteractive {
    //a class which make to interactive with player by chat message.
    public static void sendMsg(CommandSender sender,String text,Color textColor){
        sender.sendMessage(ChatColor.of(new Color(0x0080FF))+"[infinityCheck]:"+ChatColor.of(textColor)+text);
    }
}
