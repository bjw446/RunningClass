import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SafeBattle {

    private static Random random = new Random();
    private List<Champion> members = new ArrayList<>();

    public void addMember(Champion champion) {
        members.add(champion);
    }
    public List<Champion> getMembers() {
        return members;
    }
    private static Champion randomPick(List<Champion> list) {
        return list.get(random.nextInt(list.size()));
    }
    public static void oneVsOne(SafeBattle team1, SafeBattle team2) {
        Champion champion1 = randomPick(team1.getMembers());
        Champion champion2 = randomPick(team2.getMembers());
        champion1.character();
        champion2.character();

        System.out.println("=====[ 1 : 1 랜덤 배틀 ]======");
        System.out.println(champion1.getName() + " VS " + champion2.getName());

        while (champion1.alive() && champion2.alive()) {
            int choice = random.nextInt(3);
            if(champion1.alive()){
                switch (choice){
                    case 0 :
                        champion1.useQ(champion2);
                        break;
                    case 1 :
                        champion1.useW(champion2);
                        break;
                    case 2 :
                        champion1.basicAttack(champion2);
                        break;
                }
            }
            int choice2 = random.nextInt(3);
            if(champion2.alive()){
                switch (choice2){
                    case 0 :
                        champion2.useQ(champion1);
                        break;
                    case 1 :
                        champion2.useW(champion1);
                        break;
                    case 2 :
                        champion2.basicAttack(champion1);
                        break;
                }
            }
        }
    }
}
