class Weapon
    def initialize(power, uses)
        @power = power
        @uses = uses
    end

    def attack
        use = 0

        if @uses > 0
            use = @power
            @uses -= 1
        end
        
        return use
    end
    
    def to_s 
        "w[#{@power},#{@uses}]"
    end

    def discard()
        dice = Dice.new()
        return dice.discard_element(@uses)
    end
end