require_relative 'combat_element'

class Weapon < Combat_element
  def initialize(power, uses)
    super(power, uses)
  end

  def attack
    return produce_effect()
  end

  def to_s
    "W" + super.to_s
  end

  public_class_method :new
end