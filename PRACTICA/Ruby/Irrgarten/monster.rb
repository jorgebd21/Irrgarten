class Monster
    @@INITIAL_HEALTH = 5
    @@INITIAL_POS = -1

    def initialize(name, intelligence, strength)
        @name = name
        @intelligence = intelligence
        @strength = strength
        @health = @@INITIAL_HEALTH
        @row = @@INITIAL_POS
        @col = @@INITIAL_POS
    end

    def dead
        return @health < 0
    end
    
    def attack
        dice = Dice.new
        return dice.intensity(@strength)
    end

    def defend(received_attack)
        is_dead = dead()
        if(!is_dead)
            dice = Dice.new()
            defensive_energy = dice.intensity(@intelligence)
            if(defensive_energy < received_attack)
                got_wounded()
                is_dead = dead()
            end
        end

        return is_dead
    end

    def set_pos(row, col)
        @row = row
        @col = col
    end

    def get_row()
        return @row
    end

    def get_col()
        return @col
    end
    
    def to_s()
        return "Monster: #{@name} (I: #{@intelligence}, S: #{@strength}, H: #{@health})\n"
    end

    private
    def got_wounded
        @health -= 1
    end
end