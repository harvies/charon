package io.github.harvies.charon.notify.telegram;

import io.github.harvies.charon.notify.NotifyProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * telegram通知服务
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class TelegramProvider extends TelegramWebhookBot implements NotifyProvider {

    public TelegramProvider(DefaultBotOptions options, TelegramProperties telegramProperties) {
        super(options);
        this.telegramProperties = telegramProperties;
    }

    private TelegramProperties telegramProperties;

    @Override
    public boolean send(String title, String text) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(telegramProperties.getChatId());
        message.setText(text);
        message.setParseMode(ParseMode.MARKDOWN);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info("Telegram send msg exception telegramProperties:[{}] title:[{}] text:[{}]", telegramProperties, title, text, e);
            return false;
        }
        return true;
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return telegramProperties.getBotToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
