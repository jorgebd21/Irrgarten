package irrgarten;

public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;

    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        row = 0;
        col = 0;
    }

    public LabyrinthCharacter(LabyrinthCharacter other){
        name = other.name;
        intelligence = other.intelligence;
        strength = other.strength;
        health = other.health;
        row = other.row;
        col = other.col;
    }

    public boolean dead(){
        return health < 0;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    protected float getIntelligence(){
        return intelligence;
    }

    protected float getStrength(){
        return strength;
    }

    protected float getHealth(){
        return health;
    }

    protected void setHealth(float health){
        this.health = health;
    }

    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString(){
        return name + " (Int: " + intelligence + ", Str: " + strength + ", Hp: " + health + ")";
    }

    protected void gotWounded(){
        health--;
    }

    public abstract float attack();
    public abstract boolean defend (float attack);
}
