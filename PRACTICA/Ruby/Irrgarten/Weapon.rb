class Weapon
    def initialize(power, uses)
        @power = power
        @uses = uses
    end

    def to_s 
        "w[#{@power},#{@uses}]"
    end
end