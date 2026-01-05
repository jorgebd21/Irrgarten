class Combat_element
  
  def initialize(effect, uses)
    @effect = effect
    @uses = uses
  end

  def produce_effect
    if @uses > 0
      @uses -= 1
      return @effect
    else
      return 0
    end
  end

  def discard
    return Dice.discard_element(@uses)
  end

  def to_s
    return "[" + @effect.to_s + "," + @uses.to_s + "]"
  end

  private_class_method :new
end