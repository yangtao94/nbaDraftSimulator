import java.util.*;

public class draftSimulator{

    private static draftSimulator singleton = new draftSimulator();
    private List<List<Integer>> combinations;
    // Instantiation
    private draftSimulator() {
        combinations = new ArrayList<>();
    }

    public static draftSimulator getInstance() {
        if (singleton == null) {
            singleton = new draftSimulator();
        }
        return singleton;
    }



    private void generateCombinations() {
        //generate 4 number combinations from 14 numbers
        combinationHelper(combinations,new ArrayList<>(),14,4,1);
        //remove final combination to reach 1000 combinations
        combinations.remove(combinations.size()-1);



    }

    private void combinationHelper(List<List<Integer>> res, List<Integer> temp, int n,int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            combinationHelper(res,temp,n,k-1,i+1);
            temp.remove(temp.size()-1);
        }
    }

    private List<Integer> generateCombi() {
        //generate the winning combination
        List<Integer> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 14; i++) {
            list.add(i);
        }
        Random num = new Random();

        for (int i = 0; i < 4; i++) {
            int index = num.nextInt(list.size());
            res.add(list.remove(index));
        }
        return res;
    }

    private boolean isAnagram(List<Integer> a, List<Integer> b) {
        a.sort(Comparator.naturalOrder());
        b.sort(Comparator.naturalOrder());
        return a.equals(b);
    }

    private boolean winningTeam(List<Integer> winning, List<List<Integer>> potential) {
            for (List<Integer> candidate : potential) {
                if (isAnagram(winning,candidate)) return true;
            }
            return false;
    }

    private void printDraftOrder(List<Franchise> draftingOrder) {

        for (int i = draftingOrder.size()-1; i >= 0; i--) {
            System.out.println("Pick Number "+(i+1)+" belongs to the: "+ draftingOrder.get(i).name);
        }


    }


    public void nbaDraft(List<String> Teams) {
        generateCombinations();
        List<Franchise> participants = new ArrayList<>();
        List<Franchise> draftOrder = new ArrayList<>();
        //Allocate combinations to teams
        int start = 0;
        for (int i  = 0; i < Teams.size(); i++) {
            // Top 3 teams get 14% of combinations
            List<List<Integer>> odds;
            while (i < 3) {
                odds = combinations.subList(start,start+140);
                participants.add(new Franchise(Teams.get(i),odds));
                start = start+140;
                i++;
            }

            switch (i+1) {
                case 4:
                    //4th place gets 12.5%
                    odds = combinations.subList(start,start+125);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+125;
                    break;
                case 5:
                    //5th place gets 10.5%
                    odds = combinations.subList(start,start+105);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+105;
                    break;
                case 6:
                    //6th place gets 9.0%
                    odds = combinations.subList(start,start+95);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+90;
                    break;
                case 7:
                    // gets 7.5%
                    odds = combinations.subList(start,start+75);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+75;
                    break;
                case 8:
                    // gets 6.0%
                    odds = combinations.subList(start,start+60);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+60;
                    break;
                case 9:
                    //gets 4.5%
                    odds = combinations.subList(start,start+45);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+45;
                    break;
                case 10:
                    // gets 3.0%
                    odds = combinations.subList(start,start+30);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+30;
                    break;
                case 11:
                    // gets 2.0%
                    odds = combinations.subList(start,start+20);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+20;
                    break;
                case 12:
                    //gets 1.5%
                    odds = combinations.subList(start,start+15);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+15;
                    break;
                case 13:
                    // gets 1.0%
                    odds = combinations.subList(start,start+10);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+10;
                    break;
                case 14:
                    // gets 0.5%
                    odds = combinations.subList(start,start+5);
                    participants.add(new Franchise(Teams.get(i),odds));
                    start = start+5;
                    break;

            }
        }

        //lottery is done four times, if combination generated is invalid / belong to the same team, redraw
        int draw = 0;

        while (draw < 4) {
            List<Integer> winningCombination = generateCombi();
            for (Franchise franchise: participants) {
                if (winningTeam(winningCombination,franchise.getDraftPicks())) {
                    draftOrder.add(franchise);
                    participants.remove(franchise);
                    draw++;
                    break;
                }
            }
        }

        //add remaining teams based on regular season record

        draftOrder.addAll(participants);

        // Print Draft Order
        printDraftOrder(draftOrder);
        System.out.println("Thank you for attending the NBA Draft Lottery");




    }

}
