class Dice
    @@MAX_USES = 5
    @@MAX_INTELLIGENCE = 10.0
    @@MAX_STRENGTH = 10.0
    @@RESURRECT_PROB = 0.3
    @@WEAPONS_REWARD = 2
    @@SHIELDS_REWARD = 3
    @@HEALTH_REWARD = 5
    @@MAX_ATTACKS = 3
    @@MAX_SHIELDS = 2

    @@generator = Random.new

    def self.random_pos(max)
        return @@generator.rand(max)
    end

    def self.who_starts(nplayers)
        return @@generator.rand(nplayers)
    end

    def self.random_intelligence()
        return @@generator.rand(@@MAX_INTELLIGENCE)
    end

    def self.random_strength()
        return @@generator.rand(@@MAX_STRENGTH)
    end

    def self.resurrect_player()
        return @@generator.rand() < @@RESURRECT_PROB
    end

    def self.weapon_reward()
        return @@generator.rand(@@WEAPONS_REWARD)
    end

    def self.shield_reward()
        return @@generator.rand(@@SHIELDS_REWARD)
    end

    def self.health_reward()
        return @@generator.rand(@@HEALTH_REWARD)
    end

    def self.weapon_power()
        return @@generator.rand(@@MAX_ATTACKS)
    end

    def self.shield_power()
        return @@generator.rand(@@MAX_SHIELDS)
    end

    def self.uses_left()
        return @@generator.rand(@@MAX_USES)
    end

    def self.intensity(competence)
        return @@generator.rand(competence)
    end

    def self.discard_element(uses_left)
        return @@generator.rand(1.0) < 1.0 - (uses_left/@@MAX_USES)
    end

    def self.next_step(preference, validMoves, intelligence)
        if(@@generator.rand(@@MAX_INTELLIGENCE) < intelligence)
            return preference
        else
            index = @@generator.rand(validMoves.size())
            return validMoves[index]
        end
    end
end