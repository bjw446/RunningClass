public class Archer extends Champion{

    public Archer(String name) {
        super(name, 1, 12, 18, 8, 8,
                120,120,150,150,11,11);}

    @Override
    public void character() {
        attackDamage = attackDamage + (int) (agilityStat * GameConstans.ARCHER_AGLITYSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.ARCHER_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.ARCHER_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.ARCHER_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        System.out.println("더블샷 !");
        if(getMp() < 15) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.ARCHER_FATALRATE_CHARACTERISTIC,GameConstans.ARCHER_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            target.takeDamage(getAttackDamage() + (int)(agilityStat*0.6));
            target.takeDamage(getAttackDamage() + (int)(agilityStat*0.6));
            mp = mp - 15;
            passive();
        }
    }

    @Override
    public void useW(Champion target) {
        System.out.println("피어싱 애로우 !");
        if(getMp() < 20) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.ARCHER_FATALRATE_CHARACTERISTIC,GameConstans.ARCHER_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            target.takeDamage(getAttackDamage() + (int)(agilityStat*1.5));
            target.defense = defense - 2;
            mp = mp - 20;
            passive();
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.ARCHER_HP_RECOVER_CHANCE, GameConstans.ARCHER_HP_CHARACTERISTIC);
        recoverMp(GameConstans.ARCHER_MP_RECOVER_CHANCE, GameConstans.ARCHER_MP_CHARACTERISTIC);
    }
}
