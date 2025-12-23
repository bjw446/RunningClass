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

    public void basicAttack(Champion target, double fatalrate, double fatalCharacteristic) {
        double fatalDamage = 0;
        if(Math.random() < fatalrate) {
            System.out.println(name + " 치명타 공격! -> " + target.name);
            fatalDamage = attackDamage + (agilityStat * fatalCharacteristic);
            target.takeDamage(fatalDamage);
        } else {
            System.out.println(name + " 기본 공격 -> " + target.name);
            target.takeDamage(attackDamage);
        }
    }

    public void takeDamage(double damage) {
        double actualDamage = damage - defense;
        if (actualDamage <= 0) {
            actualDamage = 0;
        }
        hp = (int) (hp - actualDamage);
        System.out.println(name + "이(가) " + actualDamage + "피해를 입었습니다. (남은 HP : " + hp + ")");
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
            mp = (int) (mp + (maxMp * 0.02) + (intelligenceStat * characteristic));
        }
    }
    public void recoverHp(double chance, double characteristic) {
        if(Math.random() < chance){
            hp = (int) (hp + (maxHp * 0.02) + (staminaStat * characteristic));
        }
    }








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
