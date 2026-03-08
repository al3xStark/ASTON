package com.shop.decorator;

public class NotifierFactory {

    public static Notifier emailOnly() {
        return new EmailNotifier();
    }

    public static Notifier emailAndSms() {
        return new SmsDecorator(new EmailNotifier());
    }

    public static Notifier all() {
        return new PushDecorator(new SmsDecorator(new EmailNotifier()));
    }
}
