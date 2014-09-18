/*
 * Class to setup the SMS communication via Ozeki NG gateway
 */
package SMS;

/**
 *
 * @author Prabushi
 */
import hu.ozeki.OzSMSMessage;
import hu.ozeki.OzSmsClient;
import java.io.*;
import java.util.*;

public class SmsClient extends OzSmsClient {

    //constructor
    public SmsClient(String host, int port) throws IOException, InterruptedException {
        super(host, port);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doOnMessageAcceptedForDelivery(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message accepted for delivery. ID: "
                + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveredToHandset(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message delivered to handset. ID: "
                + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveredToNetwork(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message delivered to network. ID: "
                + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveryError(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message could not be delivered. ID: "
                + sms.messageId + " Error message: " + sms.errorMessage + "\r\n");
    }

    @Override
    public void doOnMessageReceived(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message received. Sender address: "
                + sms.sender + " Message text: " + sms.messageData);
    }

    public void doOnClientConnectionError(String errorCode, String errorMessage) {
        Date now = new Date();
        System.out.println(now.toString() + " Message code: " + errorCode
                + ", Message: " + errorMessage);
    }

    @Override
    public void doOnClientConnectionError(int i, String string) {
        Date now = new Date();
        System.out.println(now.toString() + " Message code: " + i
                + ", Message: " + string);
    }
}
