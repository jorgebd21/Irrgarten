class Labyrinth_character
  def initialize(name_or_other, intelligence = nil, strength = nil, health = nil)
    if name_or_other.is_a?(Labyrinth_character)
      other = name_or_other
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      @row = other.row
      @col = other.col
    else
      @name = other
      @intelligence = intelligence
      @strength = strength
      @health = health
      @row = 0
      @col = 0
    end
  end

  def dead
    return @health < 0
  end

  def set_pos(row, col)
    @row = row
    @col = col
  end

  def to_s
    "#{@name} (Int: #{@intelligence}, Str: #{@strength}, Hp: #{@health})"
  end

  protected

  def intelligence
    return @intelligence
  end

  def strength
    return @strength
  end

  def health
    return @health
  end

  def health=(value)
    @health = value
  end

  def got_wounded
    @health -= 1
  end

  public

  def attack
  end

  def defend(received_attack)
  end

  private_class_method :new
end