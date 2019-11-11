import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Task {
  private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
  InputStream in;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Order order = new Order();
  private static PayStrategy paymentOption;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while(!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);

                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);

                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (paymentOption == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                if(paymentMethod.equals("1")) {
                    paymentOption = new PaybyPayPal();
                } else {
                    paymentOption = new PayByCreditCard();
                }

                order.processOrder(paymentOption);

                System.out.println("Pay" + order.getTotalCost() + " units or continue shopping");
                String proceed = reader.readLine();

                if(proceed.equalsIgnoreCase("P")) {
                    if(paymentOption.pay(order.getTotalCost())) {
                        System.out.println("Payment has successful");
                    } else {
                        System.out.println("Payment failed");
                    }
                    order.setClosed();
                }
            }
        }
    }

}