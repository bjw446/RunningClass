public class Wizard extends Champion{

    public Wizard(String name) {
        super(name, 1, 8, 8, 20, 10,
                100,100,200,200,10,10);}

    @Override
    public void character() {
        attackDamage = attackDamage + (int) (intelligenceStat * GameConstans.WIZARD_INTELLIGENCESTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.WIZARD_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.WIZARD_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.WIZARD_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        System.out.println("파이어볼 !");
        if(getMp() < 20) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.WIZARD_FATALRATE_CHARACTERISTIC,GameConstans.WIZARD_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            target.takeDamage((int) (intelligenceStat*2.5));
            mp = mp - 20;
            passive();
        }
    }

    @Override
    public void useW(Champion target) {
        System.out.println("아케인 쉴드 !");
        if(getMp() < 25) {
            System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
            basicAttack(target,GameConstans.WIZARD_FATALRATE_CHARACTERISTIC,GameConstans.WIZARD_FATALDAMAGE_CHARACTERISTIC);
            passive();
        }else {
            defense = defense + (int) (intelligenceStat*1.2);
            mp = mp - 25;
            passive();
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.WIZARD_HP_RECOVER_CHANCE, GameConstans.WIZARD_HP_CHARACTERISTIC);
        recoverMp(GameConstans.WIZARD_MP_RECOVER_CHANCE, GameConstans.WIZARD_MP_CHARACTERISTIC);
    }
}
