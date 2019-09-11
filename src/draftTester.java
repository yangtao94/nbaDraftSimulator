import java.util.ArrayList;
import java.util.List;

public class draftTester {
    public static void main(String[] args) {
        draftSimulator tmp = draftSimulator.getInstance();
        List<String> Teams = new ArrayList<>() {{
            add("Lakers");
            add("Celtics");
            add("Warriors");
            add("76ers");
            add("Kings");
            add("Bulls");
            add("Pelicans");
            add("Magic");
            add("Clippers");
            add("Raptors");
            add("Hawks");
            add("Suns");
            add("Cavaliers");
            add("Hornets");

        }};
        tmp.nbaDraft(Teams);
    }
}
