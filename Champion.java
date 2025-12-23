public abstract class Champion {
    String name;
    int level;
    int hp;
    int attackDamage;
    int defense;

    public Champion(String name, int level, int hp, int attackDamage, int defense) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.defense = defense;
    }

    public void basicAttack(Champion target) {
        System.out.println(name + " 기본 공격 -> " + target.name);
        target.takeDamage(attackDamage);
    }

    public void takeDamage(int damage) {
        damage = attackDamage - defense;
        if (attackDamage - defense == 0) {
            damage = 0;
        }
        hp = hp - damage;
        System.out.println(name + "이 " + damage + "피해를 입었습니다. (남은 HP : " + hp + ")");
    }

    public void levelUp(){
        level = level + 1;
        hp = (int) (hp + (hp * 0.1));
        attackDamage = (int) (attackDamage + (attackDamage * 0.05));
        defense = (int) (defense + (defense * 0.05));
    }

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
}
