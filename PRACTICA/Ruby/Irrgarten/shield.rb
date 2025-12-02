class Shield < CombatElement
    def initialize(protection, uses)
        super(protection, uses)
    end

    def protect()
        return produce_effect()
    end

    def to_s()
        return "S" + super.to_s()
    end

    public_class_method :new
end