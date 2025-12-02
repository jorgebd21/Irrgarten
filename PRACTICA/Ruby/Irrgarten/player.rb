require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'
require_relative 'labyrinth_character'

class Player < Labyrinth_character
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@HITS2LOSE = 3

    def initialize(number_or_other, intelligence, strength)
        if number_or_other.is_a?(Player)
            other = number_or_other
            super(other)
            @number = other.get_number()
            @weapons = other.weapons.clone
            @shields = other.shields.clone
            @consecutive_hits = other.consecutive_hits
        else
            super("Player #" + number_or_other.to_s, intelligence, strength, @@INITIAL_HEALTH)
            @consecutive_hits = 0
            @weapons = Array.new(@@MAX_WEAPONS)   
            @shields = Array.new(@@MAX_SHIELDS)

            for i in 0...@@MAX_WEAPONS
                w = Weapon.new(Dice.weapon_power(), Dice.uses_left())
                @weapons[i] = w
            end

            for i in 0...@@MAX_SHIELDS
                s = Shield.new(Dice.shield_power(), Dice.uses_left())
                @shields[i] = s
            end
        end
    end

    attr_reader :weapons, :shields, :consecutive_hits

    def get_number()
        return @number
    end

    def resurrect()
        @weapons.clear
        @shields.clear
        @health = @@INITIAL_HEALTH
        @consecutive_hits = 0
    end

    def move(direction, valid_moves)
        size = valid_moves.size()
        contained = valid_moves.include?(direction)

        if((size > 0) && !contained)
            return valid_moves[0]
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
        w_reward = Dice.weapon_reward()
        s_reward = Dice.shield_reward()

        for i in 0...w_reward
            wnew = new_weapon()
            receive_weapon(wnew)
        end
        for i in 0...s_reward
            snew = new_shield()
            receive_shield(snew)
        end

        extra_health = Dice.health_reward()
        @health += extra_health
    end

    def to_s()
        weapon_s = ""
        shield_s = ""
        for i in 0...@weapons.size()
            weapon_s += @weapons[i].to_s()
        end
        for i in 0...@shields.size()
            shield_s += @shields[i].to_s()
        end
        return super.to_s + "Armas: #{@weapons.size}: " + weapon_s + ", Escudos: #{@shields.size}: " + shield_s + "\n"
    end
    
    private
    def receive_weapon(weapon)
        for w in @weapons
            discard = w.discard()
            if (discard)
                @weapons.delete(w)
            end
        end

        size = @weapons.size()
        if(size < @@MAX_WEAPONS)
            @weapons.push(weapon)
        end 
    end

    def receive_shield(shield)
        for s in @shields
            discard = s.discard()
            if (discard)
                @shields.delete(s)
            end
        end

        size = @shields.size()
        if(size < @@MAX_SHIELDS)
            @shields.push(shield)
        end 
    end

    def new_weapon()
        return Weapon.new(Dice.weapon_power(), Dice.uses_left())
    end

    def new_shield()
        return Shield.new(Dice.shield_power(), Dice.uses_left())
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

    def in_consecutive_hits()
        @consecutive_hits += 1
    end

    public_class_method :new
end