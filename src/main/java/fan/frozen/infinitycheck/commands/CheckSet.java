package fan.frozen.infinitycheck.commands;

import fan.frozen.infinitycheck.chatInteractive.ChatInteractive;
import fan.frozen.infinitycheck.configuration.UserData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.awt.*;

public class CheckSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //check if sender have enough permission to apply this command, if not send the sender notification
        if (commandSender.hasPermission("infinityCheck.permission.createChecks")||commandSender.isOp()){
            if (strings.length>=2){
                //create an instance of userData for further operation
                UserData data = new UserData();
                int integer = Integer.parseInt(strings[1]);
                //get value of the code from command input and save it to yml file
                data.setUserData(strings[0],integer);
                //send notification to command sender to remind him the code created successfully
                ChatInteractive.sendMsg(commandSender,"兑换码 "+strings[0]+" 创建成功",new Color(0x3BC70C));
            }
        }else {
            //if the command sender don't have enough permission, send message to remind him
            ChatInteractive.sendMsg(commandSender,"您没有足够的权限执行此指令，请联系管理员获取更多帮助",new Color(0xA60202));
        }
        return false;
    }
}
