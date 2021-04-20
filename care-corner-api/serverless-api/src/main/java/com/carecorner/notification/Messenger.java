package com.carecorner.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Messenger {
  private static final String ACCOUNT_SID = System.getenv("TWILIO_SID");
  private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  public static void sendSMS(String phoneNumber, String txt) {
    System.out.println(ACCOUNT_SID);
    System.out.println(AUTH_TOKEN);
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    String careCornerNumber = "+12028997947";
    Message message = Message.creator(new PhoneNumber("+1" + phoneNumber),
         new PhoneNumber(careCornerNumber), 
         txt)
    .create(); 

    System.out.println(message.getSid());
  }
}