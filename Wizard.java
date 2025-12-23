public class Wizard extends Champion{

    public Wizard(String name) {
        super(name, 1, 8, 8, 20, 10,
                120,120,150,150,10,11);}

    private int useQCount = 0;
    private int useWCount = 0;
    private int useWStay = 0;
    int useWBuff = (int) (intelligenceStat * 1.2);

    public void countTurnOff(){
        if(useQCount > 0) {
            useQCount = useQCount - 1;
        }
        if(useWCount > 0) {
            useWCount = useWCount - 1;
        }
        if(useWStay > 0) {
            useWStay = useWStay - 1;
            if(useWStay == 0) {
                defense = defense - useWBuff;
                System.out.println(getName() + "의 아케인 쉴드 효과가 사라졌습니다.");
            }
        }
        passive();
    }

    @Override
    public void basicAttack(Champion target) {
        double fatalDamage = 0;
        if(Math.random() < GameConstans.WIZARD_FATALRATE_CHARACTERISTIC) {
            System.out.println(getName() + "의 치명타 공격! -> " + target.getName());
            fatalDamage = attackDamage + (agilityStat * GameConstans.WIZARD_FATALDAMAGE_CHARACTERISTIC);
            target.takeDamage((int)fatalDamage);
            countTurnOff();
        } else {
            System.out.println(getName() + "의 기본 공격 -> " + target.getName());
            target.takeDamage(attackDamage);
            countTurnOff();
        }
    }

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
        if(useQCount == 0) {
            if(getMp() < 70) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 70 && useQCount != 0) {
                System.out.println("파이어볼 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useQCount);
                basicAttack(target);
            }else {
                mp = mp - 70;
                System.out.println(getName() + "의 파이어볼 ! (남은 MP : " + mp + ")");
                target.takeDamage((int) (intelligenceStat*2.5));
                useQCount = 2;
            }
        }else {
            System.out.println("파이어볼 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useQCount);
            basicAttack(target);
        }
    }

    @Override
    public void useW(Champion target) {
        if(useWCount == 0) {
            if(getMp() < 75) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 75 && useWCount != 0) {
                System.out.println("아케인 쉴드 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useWCount);
                basicAttack(target);
            }else {
                mp = mp - 75;
                System.out.println(getName() + "의 아케인 쉴드 ! (남은 MP : " + mp + ")");
                defense = defense + useWBuff;
                System.out.println("방어력 : " + useWBuff +" 증가, 현재 방어력 : " + defense + ")");
                useWCount = 5;
                useWStay = 3;
            }
        }else {
            System.out.println("아케인 쉴드 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useWCount);
            basicAttack(target);
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.WIZARD_HP_RECOVER_CHANCE, GameConstans.WIZARD_HP_CHARACTERISTIC);
        recoverMp(GameConstans.WIZARD_MP_RECOVER_CHANCE, GameConstans.WIZARD_MP_CHARACTERISTIC);
    }
}
