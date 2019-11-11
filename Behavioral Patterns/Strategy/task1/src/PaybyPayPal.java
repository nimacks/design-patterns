import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PaybyPayPal implements PayStrategy {
    private static final Map<String, String> REPOSITORY = new HashMap<>();
    InputStream in;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    private String email;
    private String password;
    private Boolean signedIn;

    static {
        REPOSITORY.put("johndoe192", "John@doe.com");
        REPOSITORY.put("Janedoe920", "jane@doe.com");
    }

    @Override
    public void collectPaymentDetails() {

        try {
            while (!signedIn) {

                System.out.println("Enter the user's email");
                email = reader.readLine();

                System.out.println("Enter the password");
                password = reader.readLine();

                if (verify()) {
                    System.out.println("Data verification has succeeded");
                } else {
                    System.out.println("Wrong email or password!");
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Paying " + paymentAmount + "using Paypal");
            return true;
        } else {
            return false;
        }

    }


    private boolean verify() {
        return true;
    }


}
