class Game_state
    def initialize(lab, p, m, c, w, l)
        @labyrinth = lab
        @players = p
        @monsters = m
        @current_player = c
        @winner = w
        @log = l
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