public class Archer extends Champion{

    public Archer(String name) {
        super(name, 1, 12, 18, 8, 8,
                130,130,150,150,11,12);}

    private int useQCount = 0;
    private int useWCount = 0;
    private int useWStay = 0;
    private int useWBuff = (int) (agilityStat * 0.35);
    private int useWBuff2 = (int) (powerStat * 0.15);
    private int useECount = 0;
    private int useRCount = 3;

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
                agilityStat = agilityStat - useWBuff;
                powerStat = powerStat - useWBuff2;
                System.out.println(getName() + "의 사냥 본능 효과가 사라졌습니다.");
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
        attackDamage = attackDamage + (int) (agilityStat * GameConstans.ARCHER_AGLITYSTAT_CHARACTERISTIC)
        + (int) (powerStat * GameConstans.ARCHER_POWERSTAT_CHARACTERISTIC);
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
                target.takeDamage(getAttackDamage() + (int)(agilityStat*0.4) + (int)(powerStat*0.2));
                target.takeDamage(getAttackDamage() + (int)(agilityStat*0.4) + (int)(powerStat*0.2));
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
            if(getMp() < 55) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 55 && useWCount != 0) {
                System.out.println("사냥 본능 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useWCount);
                basicAttack(target);
            }else {
                mp = mp - 55;
                System.out.println(getName() + "의 사냥 본능 ! (남은 MP : " + mp + ")");
                agilityStat = agilityStat + useWBuff;
                powerStat = powerStat + useWBuff2;
                System.out.println("민첩성 : " + useWBuff +" 증가, 현재 민첩성 : " + agilityStat + ")");
                System.out.println("근력 : " + useWBuff2 +" 증가, 현재 근력 : " + powerStat + ")");
                useWCount = 4;
                useWStay = 2;
            }
        }else {
            System.out.println("사냥 본능 재사용 턴이 아직 남아 있습니다.");
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
                System.out.println("피어싱 애로우 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useECount);
                basicAttack(target);
            }else {
                mp = mp - 65;
                System.out.println(getName() + "의 피어싱 애로우 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*1.0) + (int)(powerStat*0.5));
                useECount = 4;
            }
        }else {
            System.out.println("피어싱 애로우 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useECount);
            basicAttack(target);
        }
    }

    @Override
    public void useR(Champion target) {
        if(useRCount == 0) {
            if(getMp() < 95) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 95 && useRCount != 0) {
                System.out.println("데스 애로우 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useRCount);
                basicAttack(target);
            }else {
                mp = mp - 95;
                System.out.println(getName() + "의 데스 애로우 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*1.8) + (int)(powerStat*1.0));
                useECount = 6;
            }
        }else {
            System.out.println("데스 애로우 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useRCount);
            basicAttack(target);
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.ARCHER_HP_RECOVER_CHANCE, GameConstans.ARCHER_HP_CHARACTERISTIC);
        recoverMp(GameConstans.ARCHER_MP_RECOVER_CHANCE, GameConstans.ARCHER_MP_CHARACTERISTIC);
    }
}
