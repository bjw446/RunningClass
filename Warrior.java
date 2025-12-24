public class Warrior extends Champion{

    public Warrior(String name) {
        super(name, 1, 18, 8, 6, 14,
                140,140,100,100,12,12);}

    private int useQCount = 0;
    private int useWCount = 0;
    private int useWStay = 0;
    private int useWBuff = (int) (staminaStat * 1.4);
    private int useECount = 0;
    private int useRCount = 2;

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
                System.out.println(getName() + "의 아이언 월 효과가 사라졌습니다.");
            }
        }
        if(useECount > 0) {
            useECount = useECount - 1;
        }
        if(useRCount > 0) {
            useRCount = useRCount - 1;
        }
        passive();
    }
    @Override
    public void basicAttack(Champion target) {
        double fatalDamage = 0;
        if(Math.random() < GameConstans.WARRIOR_FATALRATE_CHARACTERISTIC) {
            System.out.println(getName() + " 치명타 공격! -> " + target.getName());
            fatalDamage = attackDamage + (agilityStat * GameConstans.WARRIOR_FATALDAMAGE_CHARACTERISTIC);
            target.takeDamage((int)fatalDamage);
            countTurnOff();
        } else {
            System.out.println(getName() + " 기본 공격 -> " + target.getName());
            target.takeDamage(attackDamage);
            countTurnOff();
        }
    }

    @Override
    public void character() {
        attackDamage = attackDamage + (int) (powerStat * GameConstans.WARRIOR_POWERSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.WARRIOR_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.WARRIOR_STAMINASTAT_CHARACTERISTIC * 3.5);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.WARRIOR_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        if(useQCount == 0) {
            if(getMp() < 50) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 50 && useQCount != 0) {
                System.out.println("파워 스트라이크 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useQCount);
                basicAttack(target);
            }else {
                mp = mp - 50;
                System.out.println(getName() + "의 파워 스트라이크 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(powerStat*1.5));
                useQCount = 3;
            }
        }else {
            System.out.println("파워 스트라이크 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useQCount);
            basicAttack(target);
        }
    }

    @Override
    public void useW(Champion target) {
        if(useWCount == 0) {
            if(getMp() < 55) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 55 && useWCount != 0) {
                System.out.println("아이언 월 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useWCount);
                basicAttack(target);
            }else {
                mp = mp - 55;
                System.out.println(getName() + "의 아이언 월 ! (남은 MP : " + mp + ")");
                defense = defense + useWBuff;
                System.out.println("방어력 : " + useWBuff +" 증가, 현재 방어력 : " + defense + ")");
                useWCount = 4;
                useWStay = 2;
            }
        }else {
            System.out.println("아이언 월 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useWCount);
            basicAttack(target);
        }
    }

    @Override
    public void useE(Champion target) {
        if(useECount == 0) {
            if(getMp() < 65) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 65 && useECount != 0) {
                System.out.println("파워 슬래시 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useECount);
                basicAttack(target);
            }else {
                mp = mp - 65;
                System.out.println(getName() + "의 파워 슬래시 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(powerStat*1.8));
                useECount = 3;
            }
        }else {
            System.out.println("파워 슬래시 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useECount);
            basicAttack(target);
        }
    }

    @Override
    public void useR(Champion target) {
        if(useRCount == 0) {
            if(getMp() < 90) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 90 && useRCount != 0) {
                System.out.println("영웅의 일격 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useRCount);
                basicAttack(target);
            }else {
                mp = mp - 90;
                System.out.println(getName() + "의 영웅의 일격 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(powerStat*3.0));
                useRCount = 6;
            }
        }else {
            System.out.println("영웅의 일격 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useRCount);
            basicAttack(target);
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.WARRIOR_HP_RECOVER_CHANCE, GameConstans.WARRIOR_HP_CHARACTERISTIC);
        recoverMp(GameConstans.WARRIOR_MP_RECOVER_CHANCE, GameConstans.WARRIOR_MP_CHARACTERISTIC);
    }
}
