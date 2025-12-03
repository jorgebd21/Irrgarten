class Labyrinth_character
  def initialize(name_or_other, intelligence = nil, strength = nil, health = nil)
    if name_or_other.is_a?(Labyrinth_character)
      other = name_or_other
      @name = other.get_name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      @row = other.get_row
      @col = other.get_col
    else
      @name = name_or_other
      @intelligence = intelligence
      @strength = strength
      @health = health
      @row = 0
      @col = 0
    end
  end

  def get_name
    return @name
  end

  def dead
    return @health < 0
  end

  def get_row
    return @row
  end

  def get_col
    return @col
  end

  def set_pos(row, col)
    @row = row
    @col = col
  end

  def to_s
    return "#{@name}, Fuerza: #{@strength}, Inteligencia: #{@intelligence}, Salud: #{@health}\n"
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