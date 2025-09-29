class Shield
    def initialize(protection, uses)
        @protection = protection
        @uses = uses
    end

    def to_s 
        "w[#{@protection},#{@uses}]"
    end
end