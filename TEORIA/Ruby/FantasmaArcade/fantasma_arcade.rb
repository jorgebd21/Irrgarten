class FantasmaArcade

  @@velocidad = 0

  def initialize(color, posicion_x, posicion_y)
    @color = color
    @posicion_x = posicion_x
    @posicion_y = posicion_y
  end

  def self.setvelocidad(v)
    @@velocidad = v
  end

  def to_s
    "Color: #{@color}, Posición: (#{@posicion_x}, #{@posicion_y}), Velocidad: #{@@velocidad}"
  end
end

f = FantasmaArcade.new("rojo", 2, 3)
puts f.to_s()
FantasmaArcade.setvelocidad(5)
puts f.to_s()