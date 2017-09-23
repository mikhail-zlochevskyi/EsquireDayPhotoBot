import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by mikhail_z on 9/23/17.
 */
public class EsquireDayPhotoBot extends TelegramLongPollingBot {


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
        return "425218487:AAF5VqmTEDuJ1O27bfY9yaCGskSdQOWhzSA";
    }


    private LocalDate getYesterday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate.minusDays(1)));
        return localDate.minusDays(1);
    }
}
