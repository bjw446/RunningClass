public class Assassin extends Champion{

    public Assassin(String name) {
        super(name, 1, 12, 22, 6, 6,
                120,120,120,120,13,10);}

    private int useQCount = 0;
    private int useWCount = 0;
    private int useWStay = 0;
    private int useWBuff = (int) (agilityStat * 0.65);
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
                agilityStat = agilityStat - useWBuff;
                System.out.println(getName() + "의 쉐도우 스텝 효과가 사라졌습니다.");
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
        if(Math.random() < GameConstans.ASSASSIN_FATALRATE_CHARACTERISTIC) {
            System.out.println(getName() + "의 치명타 공격! -> " + target.getName());
            fatalDamage = attackDamage + (agilityStat * GameConstans.ASSASSIN_FATALDAMAGE_CHARACTERISTIC);
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
        attackDamage = attackDamage + (int) (agilityStat * GameConstans.ASSASSIN_AGLITYSTAT_CHARACTERISTIC);
        defense = defense + (int) (staminaStat * GameConstans.ASSASSIN_STAMINASTAT_CHARACTERISTIC);
        hp = hp + (int) (staminaStat * GameConstans.ASSASSIN_STAMINASTAT_CHARACTERISTIC * 4);
        maxHp = hp;
        mp = mp + (int) (intelligenceStat * GameConstans.ASSASSIN_INTELLIGENCESTAT_CHARACTERISTIC * 4);
        maxMp = mp;
    }

    @Override
    public void useQ(Champion target) {
        if(useQCount == 0) {
            if(getMp() < 50) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 50 && useQCount != 0) {
                System.out.println("백스텝 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useQCount);
                basicAttack(target);
            }else {
                mp = mp - 50;
                System.out.println(getName() + "의 백스텝 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*2));
                useQCount = 2;
            }
        }else {
            System.out.println("백스텝 재사용 턴이 아직 남아 있습니다.");
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
                System.out.println("쉐도우 스텝 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useWCount);
                basicAttack(target);
            }else {
                mp = mp - 55;
                System.out.println(getName() + "의 쉐도우 스텝 ! (남은 MP : " + mp + ")");
                agilityStat = agilityStat + useWBuff;
                System.out.println("민첩성 : " + useWBuff +" 증가, 현재 민첩성 : " + agilityStat + ")");
                useWCount = 3;
                useWStay = 2;
            }
        }else {
            System.out.println("쉐도우 스텝 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useWCount);
            basicAttack(target);
        }
    }

    @Override
    public void useE(Champion target) {
        if(useECount == 0) {
            if(getMp() < 60) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 60 && useECount != 0) {
                System.out.println("블러드 리퍼 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useECount);
                basicAttack(target);
            }else {
                mp = mp - 60;
                System.out.println(getName() + "의 블러드 리퍼 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*2.2));
                if(hp + (int)(agilityStat*2.2) >= maxHp){
                    int realRecover = maxHp - hp;
                    hp = maxHp;
                    System.out.println("블러드 리퍼 흡혈 효과 : " + realRecover + " (남은 HP : " + hp + ")");
                }else{
                    hp = hp + (int)(agilityStat*2.2);
                    System.out.println("블러드 리퍼 흡혈 효과 : " + (int)(agilityStat*2.2) + " (남은 HP : " + hp + ")");
                }
                useECount = 4;
            }
        }else {
            System.out.println("블러드 리퍼 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useECount);
            basicAttack(target);
        }
    }

    @Override
    public void useR(Champion target) {
        if(useRCount == 0) {
            if(getMp() < 100) {
                System.out.println("마나가 부족하여 스킬을 사용할 수 없습니다.");
                basicAttack(target);
            } else if(getMp() > 100 && useRCount != 0) {
                System.out.println("블레이드 댄스 재사용 턴이 아직 남아 있습니다.");
                System.out.println("남은 재사용 턴 : " + useRCount);
                basicAttack(target);
            }else {
                mp = mp - 100;
                System.out.println(getName() + "의 블레이드 댄스 ! (남은 MP : " + mp + ")");
                target.takeDamage(getAttackDamage() + (int)(agilityStat*3.0));
                useRCount = 6;
            }
        }else {
            System.out.println("블레이드 댄스 재사용 턴이 아직 남아 있습니다.");
            System.out.println("남은 재사용 턴 : " + useRCount);
            basicAttack(target);
        }
    }

    @Override
    public void passive() {
        recoverHp(GameConstans.ASSASSIN_HP_RECOVER_CHANCE, GameConstans.ASSASSIN_HP_CHARACTERISTIC);
        recoverMp(GameConstans.ASSASSIN_MP_RECOVER_CHANCE, GameConstans.ASSASSIN_MP_CHARACTERISTIC);
    }
}
