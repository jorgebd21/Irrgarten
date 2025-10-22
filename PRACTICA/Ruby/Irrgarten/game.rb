class Game
    @@MAX_ROUNDS = 10

    def initialize(nplayers)
        @current_player_index = nplayers - 1
        @log = ""
        @players = Array.new(nplayers)
        @labyrinth = Labyrinth.new(10, 10, 9, 9)
        @monsters = Array.new
        configure_labyrinth
    end

    def finished
        return @labyrinth.have_a_winner
    end

    def next_step(preferred_direction)
    end

    def get_game_state
        return GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @current_player_index, finished, @log)
    end

    def configure_labyrinth
        # Hace falta diseñarlo
    end

    def next_player
        @current_player_index = (@current_player_index + 1) % @players.size
    end

    def actual_direction(preferred_direction)
    end

    def combat(monster)
    end

    def manage_reward(winner)
    end

    def manage_resurrection
    end

    def log_player_won
        @log += "Player ##{@players[@current_player_index].get_name} ha ganado el combate!\n"
    end

    def log_monster_won
        @log += "El monstruo ha ganado el combate!\n"
    end

    def log_player_resurrected
        @log += "Player ##{@players[@current_player_index].get_name} ha resucitado!\n"
    end

    def log_player_skip_turn
        @log += "Player ##{@players[@current_player_index].get_name} se salta el turno!\n"
    end

    def log_player_no_orders
        @log += "Player ##{@players[@current_player_index].get_name} no tiene órdenes!\n"
    end

    def log_no_monsters
        @log += "No hay monstruos en la nueva posición!\n"
    end

    def log_round(round, max)
        @log += "---- Ronda #{round} de #{max} ----\n"
    end
end