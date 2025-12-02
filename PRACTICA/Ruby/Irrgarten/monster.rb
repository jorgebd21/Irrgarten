require_relative 'labyrinth_character'
require_relative 'dice'

class Monster < Labyrinth_character
    INITIAL_HEALTH = 10

    def initialize(name, intelligence, strength)
        super(name, intelligence, strength, INITIAL_HEALTH)
        @row = nil
        @col = nil
    end
    
    def attack
        return Dice.intensity(@strength)
    end

    def defend(received_attack)
        is_dead = dead()
        if(!is_dead)
            defensive_energy = Dice.intensity(@intelligence)
            if(defensive_energy < received_attack)
                got_wounded()
                is_dead = dead()
            end
        end

        return is_dead
    end

    public_class_method :new
end