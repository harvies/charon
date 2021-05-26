package io.github.harvies.charon.notify;

public interface NotifyProvider {

    boolean send(String title, String text);
}
