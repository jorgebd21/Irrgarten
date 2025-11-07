
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

      # Laberinto
      begin
        lab = game_state.get_labyrinth
        puts "LABERINTO:"
        puts lab.nil? ? "(vacío)" : lab
      rescue
        puts "LABERINTO: (no disponible)"
      end
      puts "----------------------------------------"

      # Jugadores
      begin
        players = game_state.get_players
        puts "JUGADORES:"
        puts players.nil? ? "(vacío)" : players
      rescue
        puts "JUGADORES: (no disponible)"
      end
      puts "----------------------------------------"

      # Monstruos
      begin
        monsters = game_state.get_monsters
        puts "MONSTRUOS:"
        puts monsters.nil? ? "(vacío)" : monsters
      rescue
        puts "MONSTRUOS: (no disponible)"
      end
      puts "----------------------------------------"

      # Información adicional
      begin
        puts "Turno actual (índice jugador): #{game_state.get_current_player}"
      rescue
        # ignorar si no existe
      end

      # Registro (log)
      begin
        log = game_state.get_log
        unless log.nil? || log.empty?
          puts "----------------------------------------"
          if(!game_state.get_winner)
            puts "REGISTRO:"
            puts log
          else
            puts "FIN DEL JUEGO, GANADOR JUGADOR #{game_state.get_current_player()}"
          end
        end
      rescue
        # ignorar si no existe
      end

      puts "========================================"
      puts

    end

  end # class   

end # module   


