package fan.frozen.infinitycheck.commands;

import fan.frozen.infinitycheck.chatInteractive.ChatInteractive;
import fan.frozen.infinitycheck.configuration.UserData;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.awt.*;

public class CheckRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //permission check
        if (sender.hasPermission("infinityCheck.permission.removeChecks")||sender.isOp()){
            if (args.length>=1){
                UserData userData = new UserData();
                //get code from yml file, if code can't be found, then send message to remind command sender.
                if (userData.getUserData().get(args[0]) == null) {
                    ChatInteractive.sendMsg(sender,"无法找到兑换码 "+args[0],new Color(0xA60202));
                }else {
                    //if code existed, then remove it from the yml file.
                    userData.removeUserData(args[0]);
                    ChatInteractive.sendMsg(sender,"成功移除兑换码 "+args[0],new Color(0x3BC70C));
                }
            }
        }else {
            ChatInteractive.sendMsg(sender,"您没有足够的权限执行此指令，请联系管理员获取更多帮助",new Color(0xA60202));
        }
        return false;
    }
}
