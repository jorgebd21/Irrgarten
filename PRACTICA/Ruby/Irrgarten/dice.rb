class Dice
    MAX_USES = 5
    MAX_INTELLIGENCE = 10.0
    MAX_STRENGTH = 10.0
    RESURRECT_PROB = 0.3
    WEAPONS_REWARD = 2
    SHIELDS_REWARD = 3
    HEALTH_REWARD = 5
    MAX_ATTACKS = 3
    MAX_SHIELDS = 2

    def initialize
        @@generator = Random.new
    end

    def random_pos(max)
        return @@generator.rand(max)
    end

    def who_starts(nplayers)
        return @@generator.rand(nplayers)
    end

    def random_intelligence()
        return @@generator.rand(MAX_INTELLIGENCE)
    end

    def random_strength()
        return @@generator.rand(MAX_STRENGTH)
    end

    def resurrect_player()
        return @@generator.rand() < RESURRECT_PROB
    end

    def weapon_reward()
        return @@generator.rand(WEAPONS_REWARD)
    end

    def shield_reward()
        return @@generator.rand(SHIELDS_REWARD)
    end

    def health_reward()
        return @@generator.rand(HEALTH_REWARD)
    end

    def weapon_power()
        return @@generator.rand(MAX_ATTACKS)
    end

    def shield_power()
        return @@generator.rand(MAX_SHIELDS)
    end

    def uses_left()
        return @@generator.rand(MAX_USES)
    end

    def intensity(competence)
        return @@generator.rand(competence)
    end

    def discard_element(uses_left)
        return @@generator.rand(1.0) < 1.0 - (uses_left/MAX_USES)
    end
end