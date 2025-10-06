require_relative 'orientation'
require_relative 'game_character'
require_relative 'directions'
require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'


puts "Prueba P1\nArmas y Escudos"

w = Weapon.new(10, 5)
puts w.to_s
puts w.attack
puts w.to_s
puts w.discard

s = Shield.new(8, 3)
puts s.to_s
puts s.protect
puts s.to_s
puts s.discard

puts "\nEnumerados"
puts Orientation::HORIZONTAL
puts Game_character::PLAYER
puts Directions::DOWN

puts "\nTiradas"
dice = Dice.new
100.times do
    puts dice.random_pos(10)
    puts dice.who_starts(4)
    puts dice.random_intelligence
    puts dice.random_strength
    puts dice.resurrect_player
    puts dice.weapon_reward
    puts dice.shield_reward
    puts dice.health_reward
    puts dice.weapon_power
    puts dice.shield_power
    puts dice.uses_left
    puts dice.intensity(5)
    puts dice.discard_element(3)
end