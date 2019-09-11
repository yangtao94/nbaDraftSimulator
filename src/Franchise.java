import java.util.List;

public class Franchise {
    public String name;
    public List<Player> roster;
    private List<List<Integer>> draftPicks;

    public Franchise(String name, List<List<Integer>> draftPicks) {
        this.name = name;
        this.draftPicks = draftPicks;
    }

    public List<List<Integer>> getDraftPicks(){
        return this.draftPicks;
    }
}
