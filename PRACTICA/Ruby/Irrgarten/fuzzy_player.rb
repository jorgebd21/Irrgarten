require_relative 'player'
require_relative 'dice'

class Fuzzy_player < Player
  def initialize(other)
    super(other)
  end

  def move(direction, valid_moves)
    return Dice.next_step(direction, valid_moves, @intelligence)
  end

  def attack()
    return sum_weapons + Dice.intensity(@strength)
  end

  def defensive_energy()
    return sum_shields + Dice.intensity(@intelligence)
  end

  def to_s
    return "Fuzzy " + super.to_s
  end

  public_class_method :new
end