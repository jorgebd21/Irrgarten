
require 'io/console'
require_relative '../directions'

module UI

  class TextUI

    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!
    
      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "\e[A"
            puts "UP ARROW"
            output = Directions::UP
            got_input = true
          when "\e[B"
            puts "DOWN ARROW"
            output = Directions::DOWN
            got_input = true
          when "\e[C"
            puts "RIGHT ARROW"
            output = Directions::RIGHT
            got_input = true
          when "\e[D"
            puts "LEFT ARROW"
            output = Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end

    def show_game(game_state)
      if game_state.nil?
        puts "Estado del juego nulo."
        return
      end

      puts
    puts "========================================"
    puts "              ESTADO DEL JUEGO          "
    puts "========================================"
    puts

    puts "LABERINTO:"
    puts game_state.get_labyrinth
    puts "----------------------------------------"

    puts "JUGADORES:"
    puts game_state.get_players
    puts "----------------------------------------"

    puts "MONSTRUOS:"
    puts game_state.get_monsters
    puts "----------------------------------------"

    puts "REGISTRO:"
    puts "Turno actual (índice jugador): #{game_state.get_current_player}"
    puts game_state.get_log
    puts "========================================"

    if (game_state.get_winner)
      puts "¡¡¡JUEGO TERMINADO!!!"
      puts "========================================"
    end

    puts

    end

  end # class   

end # module   


