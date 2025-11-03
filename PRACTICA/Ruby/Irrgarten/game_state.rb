class Game_state
    def initialize(labyrinth, players, monsters, current_player, winner, log)
        @labyrinth = labyrinth
        @players = players
        @monsters = monsters
        @current_player = current_player
        @winner = winner
        @log = log
    end

    def get_labyrinth()
        return @labyrinth
    end

    def get_players()   
        return @players
    end

    def get_monsters()
        return @monsters
    end

    def get_current_player()
        return @current_player
    end

    def get_winner()
        return @winner
    end

    def get_log()
        return @log
    end
end