package fan.frozen.infinitycheck.commands;

import fan.frozen.infinitycheck.InfinityCheck;
import fan.frozen.infinitycheck.chatInteractive.ChatInteractive;
import fan.frozen.infinitycheck.configuration.UserData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class CheckExchange implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //default every can change checks, so this command don't have permission checking.
        if (commandSender instanceof Player){
            //the user of this command must be player, so check the sender is player or not, if it's not player, stop applying command.
            Player player = (Player) commandSender;
            if (strings.length>=1){
                //create new userData instance for further operation
                UserData userData = new UserData();
                if (userData.getUserData().get(strings[0]) == null) {
                    //if system can't find code, stop applying the command and send message to the sender to remind him.
                    ChatInteractive.sendMsg(commandSender,"无法找到兑换码 "+strings[0],new Color(0xA60202));
                    return false;
                }
                int anInt = userData.getUserData().getInt(strings[0]);
                //get code's value from yml file, if value equals 0, then do nothing, else add money to player's account.
                if (anInt!=0){
                    InfinityCheck.economy.depositPlayer(player,anInt);
                    ChatInteractive.sendMsg(commandSender,"您已通过兑换码 "+strings[0]+" 兑换了"+anInt+"$",new Color(0x3BC70C));
                }
            }
        }
        return false;
    }
}
