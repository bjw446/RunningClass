import java.util.Random;

public abstract class Champion {
    private final String name;
    int level;
    int hp;
    int attackDamage;
    int defense;
    int maxHp;
    int mp;
    int maxMp;
    int powerStat;
    int agilityStat;
    int intelligenceStat;
    int staminaStat;
    private static int createdCount;

    public Champion(String name, int level, int powerStat, int agilityStat, int intelligenceStat, int staminaStat,
                    int hp, int maxHp, int mp, int maxMp, int attackDamage, int defense) {
        this.name = name;
        this.level = level;
        this.powerStat = powerStat;
        this.agilityStat = agilityStat;
        this.intelligenceStat = intelligenceStat;
        this.staminaStat = staminaStat;
        this.hp = hp;
        this.maxHp = maxHp;
        this.mp = mp;
        this.maxMp = maxMp;
        this.attackDamage = attackDamage;
        this.defense = defense;
        createdCount++;

    }
    public void takeDamage(int damage) {
        if(hp <= 0) {
            System.out.println("챔피언이 사망 하였습니다.");
            alive();
            return;
        }
        if(Math.random() < agilityStat * 0.015) {
            System.out.println("Miss!! 공격을 회피 했습니다!");
        }else {
            int actualDamage = damage - defense;
            if (actualDamage <= 0) {
                actualDamage = 0;
            }
            if(hp - actualDamage < 0) {
                actualDamage = actualDamage - hp;
                hp = 0;
                System.out.println(name + " " + actualDamage + " 피해를 입었습니다. (남은 HP : " + hp + ")");
            }else {
                hp = (int) (hp - actualDamage);
                System.out.println(name + " " + actualDamage + " 피해를 입었습니다. (남은 HP : " + hp + ")");
            }
        }
    }
    public boolean alive() {
        if (hp <= 0) {
            System.out.println(name + " 사망하여 패배 !!");
            return false;
        }
        return true;
    }
    public void levelUp(){
        if(level >= GameConstans.MAX_LEVEL) {
            System.out.println("이미 최고 레벨 도달");
        } else {
            level = level + 1;
            maxHp = (int) (maxHp + (maxHp * 0.05) + (staminaStat * 0.2));
            hp = (int) (hp + (hp * 0.05) + (staminaStat * 0.2));
            maxMp = (int) (maxMp + (maxMp * 0.05) + (intelligenceStat * 0.2));
            mp = (int) (mp + (mp * 0.05) + (intelligenceStat * 0.2));
            attackDamage = (int) (attackDamage + (attackDamage * 0.02));
            defense = (int) (defense + (defense * 0.02));
        }

    }
    public void recoverMp(double chance, double characteristic) {
        if(Math.random() < chance) {
            if((int) (mp + (maxMp * 0.02) + (intelligenceStat * characteristic)) >= maxMp){
                mp = maxMp;
            }else{
                mp = (int) (mp + (maxMp * 0.02) + (intelligenceStat * characteristic));
                System.out.println(name + "의 마나 재생 (현재 MP : " + mp + ")");
            }
        }
    }
    public void recoverHp(double chance, double characteristic) {
        if(Math.random() < chance){
            if((int) (hp + (maxHp * 0.02) + (staminaStat * characteristic)) >= maxHp){
                hp = maxHp;
            }else{
                hp = (int) (hp + (maxHp * 0.02) + (staminaStat * characteristic));
                System.out.println(name + "의 체력 재생 (현재 HP : " + hp + ")");
            }
        }
    }




    public abstract void basicAttack(Champion target);
    public abstract void character();
    public abstract void useQ(Champion target);
    public abstract void useW(Champion target);
    public abstract void passive();
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getHp() {
        return hp;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public int getDefense() {
        return defense;
    }
    public int getMp() {
        return mp;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getMaxMp() {
        return maxMp;
    }
    public int getPowerStat() {
        return powerStat;
    }
    public int getAgilityStat() {
        return agilityStat;
    }
    public int getIntelligenceStat() {
        return intelligenceStat;
    }
    public int getStaminaStat() {
        return staminaStat;
    }
}
