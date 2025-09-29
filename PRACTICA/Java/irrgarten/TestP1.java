package irrgarten;

public class TestP1 {
    public static void main(String[] args) {

        //Prueba clases
        Weapon arma = new Weapon(2.4f,3);
        arma.attack();
        System.out.println("Ataque1: " + arma.toString());
        arma.attack();
        System.out.println("Ataque2: " + arma.toString());
        arma.attack();
        System.out.println("Ataque3: " + arma.toString());
        System.out.println("Descartar? " + arma.discard()+"\n");
        
        Shield escudo = new Shield(5.3f, 3);
        escudo.protect();
        System.out.println("Defensa1: " + escudo.toString());
        escudo.protect();
        System.out.println("Defensa2: " + escudo.toString());
        escudo.protect();
        System.out.println("Defensa3: " + escudo.toString());
        System.out.println("Descartar? " + escudo.discard()+"\n");

        Directions dir = Directions.DOWN;
        Orientation or = Orientation.HORIZONTAL;
        GameCharacter player = GameCharacter.PLAYER;

        //Prueba enumerados
        System.out.print(or + " ");
        System.out.print(player + " ");
        System.out.println(dir);

        //Prueba Dice
        for(int i=0;i<100;i++){
            System.out.println("\n -----Prueba Dice " + i + "-----");
            System.out.println("Posición random (máx 10): " + Dice.randomPos(10));
            System.out.println("Quién empieza (10 jugadores): " + Dice.whoStarts(10));
            System.out.println("Inteligencia random: " + Dice.randomIntelligence());
            System.out.println("Fuerza random: " + Dice.randomStrength());
            System.out.println("¿Resucitar jugador? " + Dice.resurrectPlayer());
            System.out.println("Recompensa de armas: " + Dice.weaponsReward());
            System.out.println("Recompensa de escudos: " + Dice.shieldsReward());
            System.out.println("Recompensa de salud: " + Dice.healthReward());
            System.out.println("Poder de arma: " + Dice.weaponPower());
            System.out.println("Poder de escudo: " + Dice.shieldPower());
            System.out.println("Usos restantes: " + Dice.usesLeft());
            float competence = 7.5f;
            System.out.println("Intensidad con competencia " + competence + ": " + Dice.intensity(competence));
            int usesLeft = 3;
            System.out.println("¿Descartar elemento con " + usesLeft + " usos restantes? " + Dice.discardElement(usesLeft) + "\n");
        }
    }
}
