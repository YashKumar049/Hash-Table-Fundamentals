import java.util.*;

class Spot {
    String plate;
    long entry;
}

public class ParkingLot {

    static Spot[] table = new Spot[10];

    static int hash(String plate) {
        return Math.abs(plate.hashCode()) % table.length;
    }

    static void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index] != null) {
            index = (index + 1) % table.length;
            probes++;
        }

        table[index] = new Spot();
        table[index].plate = plate;
        table[index].entry = System.currentTimeMillis();

        System.out.println(plate + " parked at spot " + index + " (" + probes + " probes)");
    }

    static void exitVehicle(String plate) {

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].plate.equals(plate)) {

                long time = System.currentTimeMillis() - table[i].entry;
                long minutes = time / 60000;

                table[i] = null;

                System.out.println(plate + " exited from spot " + i + " Duration: " + minutes + " minutes");
                return;
            }
        }

        System.out.println("Vehicle not found");
    }

    static void getStatistics() {

        int used = 0;

        for (Spot s : table) {
            if (s != null) used++;
        }

        double occ = (used * 100.0) / table.length;

        System.out.println("Occupancy: " + occ + "%");
    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");
        parkVehicle("ABC1235");
        parkVehicle("XYZ9999");

        exitVehicle("ABC1234");

        getStatistics();
    }
}