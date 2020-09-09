package me.discens.msgplugin.commands;

import me.discens.msgplugin.MsgPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RCommand implements CommandExecutor {
    private MsgPlugin plugin;

    public RCommand(MsgPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: Must be a player to use this command.");
            return true;
        }

        if(plugin.getLastReceived(sender.getName()) == null){
            sender.sendMessage(ChatColor.GRAY + "You have no one to reply to.");
            return true;
        }

        Player receiver = Bukkit.getPlayer(plugin.getLastReceived(sender.getName()).getLastReceived());

        if( receiver == null) {
            sender.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.GRAY + " is offline.");
            return true;
        }

        String receiverMsg = ChatColor.LIGHT_PURPLE + sender.getName() + " whispers: ";
        String senderMsg = ChatColor.LIGHT_PURPLE +"To " + receiver.getName() + ": " ;

        for (int i = 0; i < args.length; i++) {
            receiverMsg += args[i] + " ";
            senderMsg += args[i] + " ";
        }

        sender.sendMessage(senderMsg);
        receiver.sendMessage(receiverMsg);

        plugin.updateLastSent(sender.getName(), receiver.getName());
        plugin.updateLastReceived(receiver.getName(), sender.getName());

        return true;
    }
}
