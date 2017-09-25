import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Created by mikhail_z on 9/23/17.
 */
public class EsquireDayPhotoBot extends TelegramLongPollingBot {

    Properties properties = new Properties();

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.getMessage().hasText()) {
            // Set variables
            String message_text = "https://esquire.ru/potd/" + update.getMessage().getText();// update.getMessage().getText();
//            String message_text = "https://esquire.ru/potd/" + getYesterday();// update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                sendMessage(message); // Sending our message object to user
                sendMessage(new SendMessage().setChatId(chat_id).setText("Please input the date of photo in format: ddMMyyyy"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    public String getBotUsername() {
        // Return bot username
        return "EsquireDayPhotoBot";
    }

    public String getBotToken() {
        // Return bot token from BotFather
        try {
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/configs.properties")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("API.TOKEN");
    }


    private LocalDate getYesterday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate.minusDays(1)));
        return localDate.minusDays(1);
    }
}
