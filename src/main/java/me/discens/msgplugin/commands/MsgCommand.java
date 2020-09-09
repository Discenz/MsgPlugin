package me.discens.msgplugin.commands;

import me.discens.msgplugin.MsgPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {
    private MsgPlugin plugin;

    public MsgCommand(MsgPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: Must be a player to use this command.");
            return true;
        }

        Player receiver = Bukkit.getPlayer(args[0]);

        if( receiver == null) {
            sender.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.GRAY + " is offline.");
            return true;
        }

         String receiverMsg = ChatColor.LIGHT_PURPLE + sender.getName() + " whispers: ";
         String senderMsg = ChatColor.LIGHT_PURPLE +"To " + receiver.getName() + ": " ;

        for (int i = 1; i < args.length; i++) {
            receiverMsg += args[i] +" ";
            senderMsg += args[i] + " ";
        }

        sender.sendMessage(senderMsg);
        receiver.sendMessage(receiverMsg);

        plugin.updateLastSent(sender.getName(), receiver.getName());
        plugin.updateLastReceived(receiver.getName(), sender.getName());


        return true;
    }

}