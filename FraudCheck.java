import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    int time;

    Transaction(int id, int amount, String merchant, int time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
    }
}

public class FraudCheck {

    static void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int need = target - t.amount;

            if (map.containsKey(need)) {
                System.out.println("(" + map.get(need).id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    static void findTwoSumWindow(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int need = target - t.amount;

            if (map.containsKey(need)) {

                Transaction x = map.get(need);

                if (Math.abs(t.time - x.time) <= 60)
                    System.out.println("(" + x.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    static void detectDuplicates(List<Transaction> list) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        for (Transaction t : list) {

            String key = t.amount + "-" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.id);
        }

        for (String k : map.keySet()) {
            if (map.get(k).size() > 1) {
                System.out.println(k + " " + map.get(k));
            }
        }
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1, 500, "StoreA", 600));
        list.add(new Transaction(2, 300, "StoreB", 615));
        list.add(new Transaction(3, 200, "StoreC", 630));
        list.add(new Transaction(4, 500, "StoreA", 700));

        findTwoSum(list, 500);
        findTwoSumWindow(list, 500);
        detectDuplicates(list);
    }
}