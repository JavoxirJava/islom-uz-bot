package Islom_uz;

import Islom_uz.model.InlineButton;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Asos extends TelegramLongPollingBot {
    private static final String ADMIN = "1085241246";
    private static final InlineButton IB = new InlineButton();
    Map<Long, String> adminLest = new HashMap<>();
    Map<Long, String> admin2 = new HashMap<>();
    Map<Long, String> addAdmin = new HashMap<>();
    Map<Long, String> tanRegion = new HashMap<>();
    Map<Long, String> news = new HashMap<>();

    Set<String> total = new HashSet<>();
    Set<String> active = new HashSet<>();
    Set<String> delete = new HashSet<>();

    Private_static_final static_final;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();
            String firstName = message.getFrom().getFirstName();
            String lastName = message.getFrom().getLastName();
            String userName = message.getFrom().getUserName();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId.toString());
            String addAdmenId = addAdmin.get(chatId);
            String admenId = admin2.get(chatId);
            System.out.println(firstName + " " + text);
            tanRegion.put(chatId, text);
            switch (text) {
                case "/start@Islom_uz_ramadan_bot":
                case "/start":
                    total.add(chatId.toString());
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(getInlineButtonRows(static_final.regions));
                    sendMessage.setReplyMarkup(markup);
                    sendMSG(sendMessage, ("Assalomu alekum " + firstName + " bulimni tanlang"));
                    break;
                case ADMIN:
                    admin2.put(chatId, chatId.toString());
                    adminLest.put(chatId, "id: " + chatId + "\n user: @" + userName + "\n firstName: " + firstName +
                            "\n lastName: " + lastName);
                    sendMSG(sendMessage, "endi siz admensiz!!!");
                    sendMessage.setChatId(ADMIN);
                    sendMSG(sendMessage, "new admin: " + prent(message));
                    break;
                default:
                    if (chatId.toString().equals(ADMIN) && addAdmenId.equals("addAdmin")) {
                        long aLong = Long.parseLong(text);
                        admin2.put(aLong, Long.toString(aLong));
                        sendMessage.setChatId(Long.toString(aLong));
                        sendMSG(sendMessage, "siz admin buldingiz");
                        sendMessage.setChatId(ADMIN);
                        sendMSG(sendMessage, "admin muaffaqiyatle qushildi");
                    } else if (chatId.toString().equals(ADMIN) && addAdmenId.equals("delAdminLest")) {
                        long aLong = Long.parseLong(text);
                        String s4 = adminLest.get(aLong);
                        adminLest.remove(aLong);
                        admin2.remove(aLong);
                        sendMSG(sendMessage, "delete admin \n" + s4);
                    } else if (addAdmenId.equals("text") && chatId.toString().equals(admenId) || chatId.toString().equals(ADMIN)) {
                        int son = 0;
                        for (String s5 : total) {
                            son++;
                            sendMessage.setChatId(s5);
                            sendMSG(sendMessage, text);
                        }
                        sendMessage.setChatId(chatId.toString());
                        sendMSG(sendMessage, "habar " + son + " kishiga muaffaqiyatli yuborildi!!!");
                        sendMessage.setChatId(ADMIN);
                        String s = adminLest.get(chatId);
                        sendMSG(sendMessage, "text yiuborildi \n" + s + "\ntomonidan");
                    } else islom_uz_API_month(sendMessage, text);
                    break;
            }
            if (chatId.toString().equals(ADMIN) || chatId.toString().equals(admenId)) {
                adminLest.put(chatId, "id: " + chatId + "\n user: @" + userName + "\n firstName: " +
                        "" + firstName + "\n lastName: " + lastName);
                try {
                    admenButton(sendMessage, message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        } else if (update.hasCallbackQuery()) {
            SendMessage sendMessage = new SendMessage();
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Message message = update.getCallbackQuery().getMessage();
            sendMessage.setChatId(chatId.toString());
            String s3 = admin2.get(chatId);
            addAdmin.put(chatId, data);
            System.out.println(message.getFrom().getFirstName() + " " + data);
            switch (data) {
                case "back":
                    InlineKeyboardMarkup markup13 = new InlineKeyboardMarkup(getInlineButtonRows(static_final.regions));
                    sendMessage.setReplyMarkup(markup13);
                    delMSG(message);
                    sendMSG(sendMessage, data);
                    break;
                case "subscribers":
                    if (chatId.toString().equals(ADMIN)) {
                        try {
                            subscribers(message, sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else sendMSG(sendMessage, news.get(Long.parseLong(ADMIN)));
                    break;
                case "adminLest": {
                    int tr = 0;
                    for (Map.Entry<Long, String> longStringEntry : adminLest.entrySet()) {
                        tr++;
                        sendMSG(sendMessage, longStringEntry.getValue());
                    }
                    sendMSG(sendMessage, "jami admin: " + tr + " ta");
                    break;
                }
                case "delAdminLest": {
                    for (Map.Entry<Long, String> longStringEntry : adminLest.entrySet()) sendMSG(sendMessage, longStringEntry.getValue());
                    sendMSG(sendMessage, "delete admin id ");
                    break;
                }
                case "delAdmin":
                    if (s3.equals(chatId.toString())) {
                        admin2.remove(chatId);
                        adminLest.remove(chatId);
                    }
                    break;
                case "addAdmin":
                    sendMSG(sendMessage, "id ni kiriting");
                    break;
                case "text":
                    sendMSG(sendMessage, "junatmoqchi bulgan habarni kiriting");
                    break;
                case "Qoraqalpog'iston V":
                    buttons(static_final.QORAQALPOQ, message, data);
                    break;
                case "Xorazim Vil":
                    buttons(static_final.XORAZIM, message, data);
                    break;
                case "Navoiy Vil":
                    buttons(static_final.NAVOIY, message, data);
                    break;
                case "Buxoro Vil":
                    buttons(static_final.BUXORO, message, data);
                    break;
                case "Samarqand Vil":
                    buttons(static_final.SAMARQAND, message, data);
                    break;
                case "Qashqadaryo Vil":
                    buttons(static_final.QASHQADARYO, message, data);
                    break;
                case "Surxandaryo Vil":
                    buttons(static_final.SURXANDARYO, message, data);
                    break;
                case "Jizzax Vil":
                    buttons(static_final.JIZZAX, message, data);
                    break;
                case "Sirdaryo Vil":
                    buttons(static_final.SIRDARYO, message, data);
                    break;
                case "Toshkent Vil":
                    buttons(static_final.TOSHKENT, message, data);
                    break;
                case "Andijon Vil":
                    buttons(static_final.ANDIJON, message, data);
                    break;
                case "Namangan Vil":
                    buttons(static_final.NAMANGAN, message, data);
                    break;
                case "Farg'ona Vil":
                    buttons(static_final.FARGONA, message, data);
                    break;
                default:
                    islom_uz_API_month(sendMessage, data);
                    delMSG(message);
                    InlineKeyboardMarkup markup14 = new InlineKeyboardMarkup(getInlineButtonRows(static_final.regions));
                    sendMessage.setReplyMarkup(markup14);
                    sendMSG(sendMessage, "Vloyatni tanlang:");
                    break;
            }
        }
    }

    public void buttons(List<String> button, Message message, String data) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(getInlineButtonRows(button));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(markup);
        delMSG(message);
        sendMSG(sendMessage, data);
    }

    public String prent(Message message) {
        User from = message.getFrom();
        return "firstName: " + from.getFirstName() + ", lastName: " + from.getLastName() + ", userName: " + from.getUserName()
                + "\nchatId: " + from.getId() + ", messageId: " + message.getMessageId() + ", text: " + message.getText();
    }

    public void sendMSG(SendMessage message, String text) {
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void delMSG(Message message) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void admenButton(SendMessage sendMessage, Message message) throws TelegramApiException {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        if (message.getChatId().toString().equals(ADMIN)) {
            InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

            inlineKeyboardButton.setText("⚙️ adminLest \uD83D\uDC68\u200D\uD83D\uDCBB");
            inlineKeyboardButton2.setText("\uD83D\uDC68\u200D\uD83D\uDCBB delAdminLest \uD83D\uDDD1");
            inlineKeyboardButton3.setText("➕ addAdmin \uD83D\uDC68\u200D\uD83D\uDCBB");
            inlineKeyboardButton4.setText("🖨️ obunachilarga habar yuborish 📡");

            inlineKeyboardButton.setCallbackData("adminLest");
            inlineKeyboardButton2.setCallbackData("delAdminLest");
            inlineKeyboardButton3.setCallbackData("addAdmin");
            inlineKeyboardButton4.setCallbackData("text");

            inlineKeyboardButtonList.add(inlineKeyboardButton);
            inlineKeyboardButtonList.add(inlineKeyboardButton2);
            inlineKeyboardButtonList2.add(inlineKeyboardButton3);
            inlineKeyboardButtonList2.add(inlineKeyboardButton4);
        } else {
            inlineKeyboardButton.setText("⚙️ adminLest \uD83D\uDC68\u200D\uD83D\uDCBB");
            inlineKeyboardButton2.setText("⚙️ admenlikni bekor qilish ❌");
            inlineKeyboardButton3.setText("🖨️ obunachilarga habar yuborish 📡");

            inlineKeyboardButton.setCallbackData("adminLest");
            inlineKeyboardButton2.setCallbackData("delAdmin");
            inlineKeyboardButton3.setCallbackData("text");

            inlineKeyboardButtonList.add(inlineKeyboardButton3);
            inlineKeyboardButtonList2.add(inlineKeyboardButton);
            inlineKeyboardButtonList2.add(inlineKeyboardButton2);
        }
        inlineButtons.add(inlineKeyboardButtonList);
        inlineButtons.add(inlineKeyboardButtonList2);
        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setText("admin uchun menu \uD83D\uDC47");
        execute(sendMessage);
    }


    public List<List<InlineKeyboardButton>> getInlineButtonRows(List<String> data) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        int length = data.size() % 2 != 0 ? data.size() - 1 : data.size();
        for (int i = 0; i < length; i += 2) {
            List<InlineKeyboardButton> inlineButton = new ArrayList<>();
            inlineButton.add(IB.getInlineButton(data.get(i), data.get(i)));
            inlineButton.add(IB.getInlineButton(data.get(i + 1), data.get(i + 1)));
            rows.add(inlineButton);
        }
        if (data.size() % 2 != 0) {
            String text = data.get(data.size() - 1);
            rows.add(Collections.singletonList(IB.getInlineButton(text, text)));
        }
        rows.add(Collections.singletonList(IB.getInlineButton("Bot Obunachilarini bilish", "subscribers")));

        return rows;
    }


    public void subscribers(Message message, SendMessage sendMessage) throws TelegramApiException   {
        int tr = 0;
        int trDel = 0;
        int trAc = 0;
        sendMessage.setChatId(message.getChatId().toString());
        for (String s : total) {
            tr++;
            if (isDeleted(s)) {
                delete.add(s);
                trDel++;
            } else {
                active.add(s);
                trAc++;
            }
        }
        String actives = "@itcity_academy\n\n" +
                "total:( \uD83D\uDC65 " + tr + " ta)\n" +
                "active:( \uD83D\uDC65 " + trAc + " ta)\n" +
                "Deleted:( \uD83D\uDC65 " + trDel + " ta)" +
                "\nObunachilar har haftada yangilanib turadi";
        news.put(Long.parseLong(ADMIN), actives);
        sendMessage.setText(actives);
        execute(sendMessage);
    }

    private boolean isDeleted(String chatId) throws TelegramApiException {
        Message message = null;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("+");
        try {
            message = execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return true;
        }
        execute(new DeleteMessage(chatId, message.getMessageId()));
        return false;
    }

    @SneakyThrows
    private void islom_uz_API_month(SendMessage sendMessage, String region) {
        HttpGet httpGet = new HttpGet("https://islomapi.uz/api/present/week?region=" + region);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        Gson gson = new Gson();
        Islom_uz_json[] jsons = gson.fromJson(reader, Islom_uz_json[].class);
        boolean isRegion = false;
        String returns = "";
        for (Islom_uz_json json : jsons) {
            isRegion = true;
            returns += "\uD83C\uDDFA\uD83C\uDDFF Region: " + json.getRegion() + "\n\uD83C\uDF16 Month: " + json.getDate()
                    + "\n\uD83D\uDCC5 Weekday: " + json.getWeekday() + "\n\uD83C\uDF19 Tong saharlik: "
                    + json.getTimes().getTong_saharlik() + "\n\uD83C\uDF1E Quyosh: " + json.getTimes().getQuyosh()
                    + "\n⏳ Peshin: " + json.getTimes().getPeshin() + "\n\uD83D\uDD70 asr: " + json.getTimes().getAsr()
                    + "\n\uD83C\uDF19 shom iftor: " + json.getTimes().getShom_iftor() + "\n⌛ hufton: "
                    + json.getTimes().getHufton() + "\n\n";
        }
        sendMSG(sendMessage, isRegion ? returns : "vloyat topilmadi!");
    }


    @Override
    public String getBotUsername() {
        return static_final.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return static_final.BOT_TOKEN;
    }
    //heroku logs --tail
}