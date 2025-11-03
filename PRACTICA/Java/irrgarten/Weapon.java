package irrgarten;

public class Weapon {
    private float power;
    private int uses;

    public Weapon(float power, int uses) {
        this.power = power;
        this.uses = uses;
    }

    public float attack() {
        float aux = 0; // Aux es una variable auxiliar para hacer solo un return estableciendolo a 0
                       // como valor base
        if (uses > 0) {
            uses--;
            aux = power;
        }
        return aux;
    }

    public String toString() {
        return "W[" + power + "," + uses + "]";
    }

    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
