public class Main {
    public static void main(String[] args) {

        ChampionPool pool = new ChampionPool();
        ChampionPool pool2 = new ChampionPool();

        pool.addChampion(new Archer("궁수"));
        pool.addChampion(new Assassin("자객"));
        pool.addChampion(new Wizard("마법사"));
        pool.addChampion(new Warrior("전사"));
        pool2.addChampion(new Archer("궁수"));
        pool2.addChampion(new Assassin("자객"));
        pool2.addChampion(new Wizard("마법사"));
        pool2.addChampion(new Warrior("전사"));

        SafeBattle team1 = new SafeBattle();
        SafeBattle team2 = new SafeBattle();

        team1.addMember(pool.get("궁수"));
        team1.addMember(pool.get("자객"));
        team1.addMember(pool.get("마법사"));
        team1.addMember(pool.get("전사"));

        team2.addMember(pool2.get("궁수"));
        team2.addMember(pool2.get("자객"));
        team2.addMember(pool2.get("마법사"));
        team2.addMember(pool2.get("전사"));

        SafeBattle.oneVsOne(team1, team2);

    }
}
