require_relative 'labyrinth'
require_relative 'dice'
require_relative 'player'
require_relative 'monster'
require_relative 'orientation'
require_relative 'game_state'
require_relative 'game_character'

class Game
    @@MAX_ROUNDS = 10

    def initialize(nplayers)
        @current_player_index = 0
        @log = ""
        @players = Array.new(nplayers)
        @labyrinth = Labyrinth.new(10, 10, 8, 8)
        @monsters = Array.new

        for i in 0...nplayers
            dice = Dice.new()
            p = Player.new((i+1).to_s(), dice.random_intelligence(), dice.random_strength())
            @players[i] = p
        end
        @current_player = @players[0]

        configure_labyrinth()
    end

    def finished()
        return @labyrinth.have_a_winner
    end

    def next_step(preferred_direction)
        @log = ""
        dead = @current_player.dead()

        if(!dead)
            direction = actual_direction(preferred_direction)

            if(direction != preferred_direction)
                log_player_no_orders()
            end

            monster = @labyrinth.put_player(direction, @current_player)

            if(monster == nil)
                log_no_monsters()
            else
                winner = combat(monster)
                manage_reward(winner, monster)
            end
        else
            manage_resurrection()
        end

        end_game = finished()
        if(!end_game)
            next_player()
        end

        return end_game
    end

    def get_game_state()
        p_s = ""
        m_s = ""
        for p in @players
            p_s += p.to_s()
        end
        for m in @monsters
            m_s += m.to_s()
        end
        return Game_state.new(@labyrinth.to_s(), p_s, m_s, @current_player_index, finished(), @log)
    end

    def configure_labyrinth()
        @labyrinth.add_block(Orientation::HORIZONTAL, 0, 0, 10);
        @labyrinth.add_block(Orientation::HORIZONTAL, 10-1, 0 , 10);
        @labyrinth.add_block(Orientation::VERTICAL, 1, 0, 10-2);
        @labyrinth.add_block(Orientation::VERTICAL, 1, 10-1, 10-2);

        @labyrinth.spread_players(@players);
    
        puntero = 0
        dice = Dice.new()
        m1 = Monster.new("A", dice.random_intelligence(), dice.random_strength())
        @monsters.push(m1)
        i = @labyrinth.random_empty_pos()
        @labyrinth.add_monster(i[0], i[1], m1)

        m2 = Monster.new("B", dice.random_intelligence(), dice.random_strength())
        @monsters.push(m2)
        i = @labyrinth.random_empty_pos()
        @labyrinth.add_monster(i[0], i[1], m2)

        m3 = Monster.new("C", dice.random_intelligence(), dice.random_strength())
        @monsters.push(m3)
        i = @labyrinth.random_empty_pos()
        @labyrinth.add_monster(i[0], i[1], m3)
    end

    def next_player()
        @current_player_index = (@current_player_index + 1) % @players.size()
        @current_player = @players[@current_player_index]
    end

    def actual_direction(preferred_direction)
        current_row = @current_player.get_row()
        current_col = @current_player.get_col()

        valid_moves = @labyrinth.valid_moves(current_row, current_col)
        output = @current_player.move(preferred_direction, valid_moves)

        return output
    end

    def combat(monster)
        round = 0
        winner = Game_character::PLAYER

        player_attack = @current_player.attack()
        lose = monster.defend(player_attack)
        while ((!lose) && (round < @@MAX_ROUNDS))
            winner = Game_character::MONSTER
            round += 1

            monster_attack = monster.attack()
            lose = @current_player.defend(monster_attack)

            if(!lose)
                winner = Game_character::PLAYER
                player_attack = @current_player.attack()
                lose = monster.defend(player_attack)
            end
        end

        log_round(round, @@MAX_ROUNDS)
        return winner
    end

    def manage_reward(winner, monster)
        if(winner == Game_character::PLAYER)
            @current_player.received_reward()
            if(monster.dead())
                @monsters.delete(monster)
                @labyrinth.remove_monster(monster.get_row(), monster.get_col())
            end
            log_player_won()
        else
            log_monster_won()
        end
    end

    def manage_resurrection()
        dice = Dice.new()
        resurrect = dice.resurrect_player()

        if(resurrect)
            @current_player.resurrect()
            log_resurrected()
        else
            log_player_skip_turn()
        end
    end

    def log_player_won()
        @log += "Player ##{@current_player.get_name()} ha ganado el combate!\n"
    end

    def log_monster_won()
        @log += "El monstruo ha ganado el combate!\n"
    end

    def log_resurrected()
        @log += "Player ##{@current_player.get_name()} ha resucitado!\n"
    end

    def log_player_skip_turn()
        @log += "Player ##{@current_player.get_name()} se salta el turno!\n"
    end

    def log_player_no_orders()
        @log += "Player ##{@current_player.get_name()} no tiene órdenes!\n"
    end

    def log_no_monsters()
        @log += "No hay monstruos en la nueva posición!\n"
    end

    def log_round(round, max)
        @log += "---- Ronda #{round} de #{max} ----\n"
    end
end