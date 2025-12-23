public class Assassin extends Champion{

    public Assassin(String name) {
        super(name, 1, 14, 20, 6, 6,
                110,110,120,120,13,9);}

    @Override
    public void character() {
        attackDamage = attackDamage + (int) (agilityStat * GameConstans.ASSASSIN_AGLITYSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.ASSASSIN_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.ASSASSIN_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.ASSASSIN_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        System.out.println("백스텝 !");
        if(getMp() < 20) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.ASSASSIN_FATALRATE_CHARACTERISTIC,GameConstans.ASSASSIN_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            target.takeDamage(getAttackDamage() + (int)(agilityStat*2));
            mp = mp - 20;
            passive();
        }
    }

    @Override
    public void useW(Champion target) {
        System.out.println("쉐도우 스텝 !");
        if(getMp() < 15) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.ASSASSIN_FATALRATE_CHARACTERISTIC,GameConstans.ASSASSIN_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            agilityStat = agilityStat + (int) (agilityStat * 0.5);
            mp = mp - 15;
            passive();
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.ASSASSIN_HP_RECOVER_CHANCE, GameConstans.ASSASSIN_HP_CHARACTERISTIC);
        recoverMp(GameConstans.ASSASSIN_MP_RECOVER_CHANCE, GameConstans.ASSASSIN_MP_CHARACTERISTIC);
    }
}
