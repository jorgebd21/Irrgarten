require_relative 'weapon'
require_relative 'shield'

class Player
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@HITS2LOSE = 3

    def initialize(number, intelligence, strength)
        @name = "Player #" + number.to_s
        @number = number
        @intelligence = intelligence
        @strength = strength
        @health = @@INITIAL_HEALTH
        @consecutive_hits = 0
        @weapons = Array.new(@@MAX_WEAPONS)   
        @shields = Array.new(@@MAX_SHIELDS)

        for i in 0...@@MAX_WEAPONS
            dice = Dice.new()
            w = Weapon.new(dice.weapon_power(), dice.uses_left())
            @weapons[i] = w
        end

        for i in 0...@@MAX_SHIELDS
            dice = Dice.new()
            s = Shield.new(dice.shield_power(), dice.uses_left())
            @shields[i] = s
        end
    end

    def get_name()
        return @name
    end

    def resurrect()
        @weapons.clear
        @shields.clear
        @health = @@INITIAL_HEALTH
        @consecutive_hits = 0
    end

    def get_row()
        return @row
    end

    def get_col()
        return @col
    end

    def get_number()
        return @number
    end

    def set_pos(row, col)
        @row = row
        @col = col
    end

    def dead()
        return (@health < 0)
    end
        
    def move(direction, valid_moves)
        size = valid_moves.size()
        contained = valid_moves.contained(direction)

        if((size > 0) && !contained)
            return valid_moves.get(0)
        else
            return direction
        end
    end

    def attack()
        return sum_weapons() + @strength
    end

    def defend(received_attack)
        return manage_hit(received_attack)
    end

    def received_reward()
        dice = Dice.new()
        w_reward = dice.weapons_reward()
        s_reward = dice.shields_reward()

        for i in 0...w_reward
            wnew = new_weapon()
            receive_weapon(wnew)
        end
        for i in 0...s_reward
            snew = new_shield()
            receive_shield(snew)
        end

        extra_health = dice.health_reward()
        @health += extra_health
    end

    def to_s()
        weapon_s = ""
        shield_s = ""
        for weapon in @weapons
            weapon_s += weapon.to_s
        end
        for shield in @shields
            shield_s += shield.to_s
        end
        return "Nombre: #{@name}, Fuerza: #{@strength}, Inteligencia: #{@intelligence}, Salud: #{@health}, Armas: #{@weapons.size}: " + weapon_s + ", Escudos: #{@shields.size}: " + shield_s
    end
    
    private
    def receive_weapon(weapon)
        for i in 0...@weapons.size()
            w = @weapons[i]
            discard = w.discard()
            if (discard)
                @weapons.delete(w)
            end
        end

        size = @weapons.size()
        if(size < @@MAX_WEAPONS)
            @weapons.push(w)
        end 
    end

    def receive_shield(shield)
        for i in 0...@shields.size()
            s = @shields[i]
            discard = s.discard()
            if (discard)
                @shields.delete(s)
            end
        end

        size = @shields.size()
        if(size < @@MAX_SHIELDS)
            @shields.push(s)
        end 
    end

    def new_weapon()
        dice = Dice.new
        return Weapon.new(dice.weapon_power(), dice.uses_left())
    end

    def new_shield()
        dice = Dice.new
        return Shield.new(dice.shield_power(), dice.uses_left())
    end

    def sum_weapons()
        sum = 0
        for weapon in @weapons
            sum += weapon.attack()
        end
        return sum
    end

    def sum_shields()
        sum = 0
        for shield in @shields
            sum += shield.protect()
        end
        return sum
    end

    def defensive_energy()
        return sum_shields() + @intelligence
    end

    def manage_hit(received_attack)
        defense = defensive_energy()

        if(defense < received_attack)
            got_wounded()
            in_consecutive_hits()
        else
            reset_hits()
        end

        lose = false
        if((@consecutive_hits == @@HITS2LOSE) || dead())
            reset_hits()
            lose = true

        end

        return lose
    end

    def reset_hits()
        @consecutive_hits = 0
    end

    def got_wounded()
        @health -= 1
    end

    def in_consecutive_hits()
        @consecutive_hits += 1
    end
end