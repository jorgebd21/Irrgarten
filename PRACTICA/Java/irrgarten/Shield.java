package irrgarten;

public class Shield {
    private float protection;
    private int uses;

    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }

    public float protect(){
        float aux = 0; // Aux es una variable auxiliar para hacer solo un return estableciendolo a 0 como valor base
        if (uses > 0) {
            uses--;
            aux = protection;
        }
        return aux;
    }

    public String toString(){
        return "S["+protection+","+uses+"]";
    }
}
