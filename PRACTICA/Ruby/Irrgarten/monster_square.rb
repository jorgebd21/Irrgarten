class Monster_square
  def initialize(row, col, monster)
    @row = row
    @col = col
    @monster = monster
  end

  def get()
    return @monster
  end

  def set(row, col, monster)
    @row = row
    @col = col
    @monster = monster
  end
end