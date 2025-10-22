class Player
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEATH = 10
    @@HITS2LOSE = 3

    def initialize(number, intelligence, strength)
        @name = "Player#{number}"
        @number = number
        @intelligence = intelligence
        @strength = strength
        @health = INITIAL_HEATH
        @consecutive_hits = 0
        @weapons = Array.new
        @shields = Array.new
    end

    def get_name
        return @name
    end

    def resurrect
        @weapons.clear
        @shields.clear
        @health = INITIAL_HEATH
        @consecutive_hits = 0
    end

    def get_row
        return @row
    end

    def get_col
        return @col
    end

    def get_number
        return @number
    end

    def set_pos(row, col)
        assert row >= 0 && col >= 0
        @row = row
        @col = col
    end

    def dead
        return (@health < 0)
    end
        
    def move(direction, valid_moves)
    end

    def attack
        return sum_weapons() + @strength
    end

    def defend(received_attack)
    end

    def received_reward
    end

    def to_s
        weapon_s = ""
        shield_s = ""
        for weapon in @weapons
            weapon_s += weapon.to_s
        end
        for shield in @shields
            shield_s += shield.to_s
        end
        return "Nombre: #{@name}, Fuerza: #{@strength}, Inteligencia: #{@intelligence}, Salud: #{@health}, Armas: #{@weapons.size}: " + weapons_s + ", Escudos: #{@shields.size}: " + shield_s
    end

    private
    def receive_weapon(weapon)
    end

    def receive_shield(shield)
    end

    def sum_weapons
        sum = 0
        for weapon in @weapons
            sum += weapon.attack()
        end
        return sum
    end

    def sum_shields
        sum = 0
        for shield in @shields
            sum += shield.protect()
        end
        return sum
    end

    def defensive_energy
    end

    def manage_hit(received_attack)
    end

    def reset_hits
        @consecutive_hits = 0
    end

    def got_wounded
        @health-= 1
    end

    def in_consecutive_hits
        return @consecutive_hits+= 1
    end
end