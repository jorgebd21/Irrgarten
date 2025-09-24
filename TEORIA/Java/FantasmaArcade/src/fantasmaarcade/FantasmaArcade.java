package fantasmaarcade;

public class FantasmaArcade {
    private static int velocidad;
    
    private String color;
    private int posicionX, posicionY;

    public FantasmaArcade(String color) {
        this.color = color;
        this.posicionX = 0;
        this.posicionY = 0;
    }
    
    public FantasmaArcade(String color, int posicionX, int posicionY) {
        this.color = color;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    
    @Override
    public String toString(){
        return "Fantasma: " + color + " en posicion (" + posicionX + "," + posicionY + ") y velocidad " + velocidad; 
    }
    
    public static void setVelocidad(int vel){
        velocidad = vel;
    }
    
    public static void main(String[] args){
        FantasmaArcade f1 = new FantasmaArcade("Rojo", 2, 4);
        FantasmaArcade f2 = new FantasmaArcade("Blanco");
        System.out.println(f1.toString());
        System.out.println(f2.toString());
    }
}
