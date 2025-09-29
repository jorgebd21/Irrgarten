class Test_p1
    def self.main()
        puts "Prueba P1\nArmas y Escudos"
        w = Weapon.new(10, 5)
        puts w.to_s
        puts w.attack
        puts w.to_s
        puts w.discard()
        s = Shield.new(8, 3)
        puts s.to_s
        puts s.protect
        puts s.to_s
        puts s.discard()

        puts "\n Enumerados"
        puts Orientation::HORIZONTAL
        puts Game_character::PLAYER
        puts Directions::DOWN

        puts "\nTiradas"
        for i in 100 do
            puts Dice.random_pos(10)
            puts Dice.who_starts(4)
            puts Dice.random_intelligence()
            puts Dice.random_strength()
            puts Dice.resurrect_player()
            puts Dice.weapon_reward()
            puts Dice.shield_reward()
            puts Dice.health_reward()
            puts Dice.weapon_power()
            puts Dice.shield_power()
            puts Dice.uses_left()
            puts Dice.intensity(5)
            puts Dice.discard_element(3)
        end
    end
end