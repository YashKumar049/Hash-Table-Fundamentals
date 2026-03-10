import java.util.*;

public class MultiLevelCache {

    static LinkedHashMap<String, String> L1 = new LinkedHashMap<>(10,0.75f,true){
        protected boolean removeEldestEntry(Map.Entry e){
            return size()>3;
        }
    };

    static HashMap<String,String> L2 = new HashMap<>();
    static HashMap<String,String> L3 = new HashMap<>();

    static int l1Hit=0,l2Hit=0,l3Hit=0;

    static String getVideo(String id){

        if(L1.containsKey(id)){
            l1Hit++;
            return "L1 HIT";
        }

        if(L2.containsKey(id)){
            l2Hit++;
            L1.put(id,L2.get(id));
            return "L2 HIT → moved to L1";
        }

        if(L3.containsKey(id)){
            l3Hit++;
            L2.put(id,L3.get(id));
            return "L3 HIT → added to L2";
        }

        return "Video not found";
    }

    static void getStatistics(){

        int total=l1Hit+l2Hit+l3Hit;

        System.out.println("L1 hits: "+l1Hit);
        System.out.println("L2 hits: "+l2Hit);
        System.out.println("L3 hits: "+l3Hit);

        if(total>0)
        System.out.println("Overall hit rate: "+(l1Hit+l2Hit+l3Hit)*100.0/total+"%");
    }

    public static void main(String[] args){

        L3.put("video1","data");
        L3.put("video2","data");
        L3.put("video3","data");

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video2"));
        System.out.println(getVideo("video2"));
        System.out.println(getVideo("video3"));

        getStatistics();
    }
}