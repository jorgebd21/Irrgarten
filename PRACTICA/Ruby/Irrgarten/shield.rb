class Shield
    def initialize(protection, uses)
        @protection = protection
        @uses = uses
    end

    def protect
        use = 0

        if @uses > 0
            use = @protection
            @uses -= 1
        end

        return use
    end

    def to_s 
        "S[#{@protection},#{@uses}]"
    end

    def discard()
        dice = Dice.new()
        return dice.discard_element(@uses)
    end
end