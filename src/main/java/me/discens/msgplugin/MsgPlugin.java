package me.discens.msgplugin;

import me.discens.msgplugin.commands.LCommand;
import org.bukkit.plugin.java.JavaPlugin;
import me.discens.msgplugin.api.LastMsg;
import me.discens.msgplugin.commands.MsgCommand;
import me.discens.msgplugin.commands.RCommand;

import java.util.ArrayList;

public class MsgPlugin extends JavaPlugin {

    private ArrayList <LastMsg> lastMsgs = new ArrayList<LastMsg>();

    @Override
    public void onEnable() {
        this.getCommand("msg").setExecutor(new MsgCommand(this));
        this.getCommand("r").setExecutor(new RCommand(this));
        this.getCommand("l").setExecutor(new LCommand(this));

    }

    public void updateLastReceived(String user, String lastMsgPlayer) {
        LastMsg lastMsg = getLastReceived(user);
        if(lastMsg == null) {
            LastMsg newMsg = new LastMsg(user);
            newMsg.setLastReceived(lastMsgPlayer);
            lastMsgs.add(newMsg);
        }
        else lastMsg.setLastReceived(lastMsgPlayer);
    }

    public void updateLastSent(String user, String lastMsgPlayer) {
        LastMsg lastMsg = getLastSent(user);
        if(lastMsg == null) {
            LastMsg newMsg = new LastMsg(user);
            newMsg.setLastSent(lastMsgPlayer);
            lastMsgs.add(newMsg);
        }
        else lastMsg.setLastSent(lastMsgPlayer);
    }

    public LastMsg getLastReceived(String user) {
        for(LastMsg lastMsg: lastMsgs) {
            if( lastMsg.getUser().equalsIgnoreCase(user) && lastMsg.getLastReceived() != null) return lastMsg;
        }
        return null;
    }

    public LastMsg getLastSent(String user) {
        for(LastMsg lastMsg: lastMsgs) {
            if( lastMsg.getUser().equalsIgnoreCase(user) && lastMsg.getLastSent() != null) return lastMsg;
        }
        return null;
    }
}
