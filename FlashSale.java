import java.util.*;

public class FlashSale {


    static HashMap<String, Integer> stock = new HashMap<>();


    static Queue<Integer> waitingList = new LinkedList<>();


    static void checkStock(String productId) {

        int s = stock.getOrDefault(productId, 0);
        System.out.println(productId + " → " + s + " units available");
    }


    static synchronized void purchaseItem(String productId, int userId) {

        int s = stock.getOrDefault(productId, 0);

        if (s > 0) {
            stock.put(productId, s - 1);
            System.out.println("User " + userId + " purchase success. Remaining: " + (s - 1));
        } 
        else {
            waitingList.add(userId);
            System.out.println("User " + userId + " added to waiting list. Position: " + waitingList.size());
        }
    }

    public static void main(String[] args) {


        stock.put("IPHONE15_256GB", 3);

        checkStock("IPHONE15_256GB");

        purchaseItem("IPHONE15_256GB", 101);
        purchaseItem("IPHONE15_256GB", 102);
        purchaseItem("IPHONE15_256GB", 103);

        // Stock finished
        purchaseItem("IPHONE15_256GB", 104);
        purchaseItem("IPHONE15_256GB", 105);
    }
}