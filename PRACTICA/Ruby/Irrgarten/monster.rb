class Monster
    @@INITIAL_HEALTH = 5
    @@INITIAL_POS = -1

    def inicialize(name, intelligence, strength)
        @name = name
        @intelligence = intelligence
        @strength = strength
        @health = INITIAL_HEALTH
        @row = INITIAL_POS
        @col = INITIAL_POS
    end

    def dead
        return @health < 0
    end
    
    def attack
        dice = Dice.new
        return dice.intensity(strength)
    end

    def defend(received_attack)
    end

    def set_pos(row, col)
        assert row >= 0 && col >= 0
        @row = row
        @col = col
    end

    def to_s
        return "Monster: #{@name} (I: #{@intelligence}, S: #{@strength}, H: #{@health}) in (#{@row}, #{@col})"
    end

    private
    def got_wounded
        @health -= 1
    end
end