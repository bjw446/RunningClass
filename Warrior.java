public class Warrior extends Champion{

    public Warrior(String name) {
        super(name, 1, 18, 8, 6, 14,
                160,160,100,100,12,14);}

    @Override
    public void character() {
        attackDamage = attackDamage + (int) (powerStat * GameConstans.WARRIOR_POWERSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.WARRIOR_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.WARRIOR_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.WARRIOR_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        System.out.println("파워 스트라이크 !");
        if(getMp() < 10) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.WARRIOR_FATALRATE_CHARACTERISTIC,GameConstans.WARRIOR_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            target.takeDamage(getAttackDamage() + (int)(powerStat*1.5));
            mp = mp - 10;
            passive();
        }
    }

    @Override
    public void useW(Champion target) {
        System.out.println("아이언 월 ! ");
        if(getMp() < 15) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.WARRIOR_FATALRATE_CHARACTERISTIC,GameConstans.WARRIOR_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            defense = defense + staminaStat * 2;
            mp = mp - 15;
            passive();
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.WARRIOR_HP_RECOVER_CHANCE, GameConstans.WARRIOR_HP_CHARACTERISTIC);
        recoverMp(GameConstans.WARRIOR_MP_RECOVER_CHANCE, GameConstans.WARRIOR_MP_CHARACTERISTIC);
    }
}
