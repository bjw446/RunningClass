public class Archer extends Champion{

    public Archer(String name) {
        super(name, 1, 12, 18, 8, 8,
                130,130,150,150,11,12);}

    private int useQCount = 0;
    private int useWCount = 0;
    private int useWStay = 0;
    int useWBuff = defense - 2;;

    public void countTurnOff(){
        if(useQCount > 0) {
            useQCount = useQCount - 1;
        }
        if(useWCount > 0) {
            useWCount = useWCount - 1;
        }
        passive();
    }

    @Override
    public void basicAttack(Champion target) {
        double fatalDamage = 0;
        if(Math.random() < GameConstans.ARCHER_FATALRATE_CHARACTERISTIC) {
            System.out.println(getName() + "의 치명타 공격! -> " + target.getName());
            fatalDamage = attackDamage + (agilityStat * GameConstans.ARCHER_FATALDAMAGE_CHARACTERISTIC);
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
        attackDamage = attackDamage + (int) (agilityStat * GameConstans.ARCHER_AGLITYSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.ARCHER_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.ARCHER_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.ARCHER_INTELLIGENCESTAT_CHARACTERISTIC * 3);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        if(useQCount == 0) {
            if(getMp() < 60) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 60 && useQCount != 0) {
                System.out.println("더블샷 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useQCount);
                basicAttack(target);
            }else {
                mp = mp - 60;
                System.out.println(getName() + "의 더블샷 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*0.6));
                target.takeDamage(getAttackDamage() + (int)(agilityStat*0.6));
                useQCount = 3;
            }
        }else {
            System.out.println("더블샷 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useQCount);
            basicAttack(target);
        }
    }

    @Override
    public void useW(Champion target) {
        if(useWCount == 0) {
            if(getMp() < 65) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 65 && useWCount != 0) {
                System.out.println("피어싱 애로우 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useQCount);
                basicAttack(target);
            }else {
                mp = mp - 65;
                System.out.println(getName() + "의 피어싱 애로우 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*1.5));
                useWCount = 4;
            }
        }else {
            System.out.println("피어싱 애로우 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useQCount);
            basicAttack(target);
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.ARCHER_HP_RECOVER_CHANCE, GameConstans.ARCHER_HP_CHARACTERISTIC);
        recoverMp(GameConstans.ARCHER_MP_RECOVER_CHANCE, GameConstans.ARCHER_MP_CHARACTERISTIC);
    }
}
