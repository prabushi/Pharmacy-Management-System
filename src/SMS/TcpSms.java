/*
 * Class to create the connection to send SMS
 */
package SMS;

import java.io.IOException;

/**
 * @author Prabushi
 */
public class TcpSms {

    public static void main(String[] args) {

        try {
            String phone = "0710415616";
            String medicine = "Vit C ";
            String phoneNo = "+94" + phone.substring(1);
            String host = "localhost";
            int port = 9500;
            String username = "admin";
            String password = "*prAbu1*";

            SmsClient osc = new SmsClient(host, port);
            osc.login(username, password);


            String line = "Your order of " + medicine + " is now available at Udupila Pharmacy. Please come and collect them at your convenience. Thank You";


            if (osc.isLoggedIn()) {
                osc.sendMessage(phoneNo, line);
                osc.logout();
            }


        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    //method that sends the SMS
    public void sendSMS(String phone, String medicine) {
        try {

            String phoneNo = "+94" + phone.substring(1);
            String host = "localhost";
            int port = 9500;
            String username = "admin";
            String password = "*prAbu1*";

            SmsClient osc = new SmsClient(host, port);
            osc.login(username, password);


            String line = "Your order of " + medicine + " is now available at Udupila Pharmacy. Please come and collect them at your convenience. Thank You";

            if (osc.isLoggedIn()) {
                osc.sendMessage(phoneNo, line);
                osc.logout();
            }


        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
