package me.discens.msgplugin.api;

public class LastMsg {

    String user, lastReceived, lastSent;

    public LastMsg(String user) {
        this.user = user;
        this.lastReceived = null;
        this.lastSent = null;
    }

    public String getUser() {
        return user;
    }

    public String getLastReceived() {
        return lastReceived;
    }

    public void setLastReceived(String lastReceived) {
        this.lastReceived = lastReceived;
    }

    public String getLastSent() {
        return lastSent;
    }

    public void setLastSent(String lastSent) {
        this.lastSent = lastSent;
    }
}
