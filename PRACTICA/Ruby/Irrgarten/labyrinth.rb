class Labyrinth
    @@BLOCK_CHAR = 'X'
    @@EMPTY_CHAR = '-'
    @@MONSTER_CHAR = 'M'
    @@COMBAT_CHAR = 'C'
    @@EXIT_CHAR = 'E'
    @@ROW = 0
    @@COL = 1

    def initialize(n_rows, n_cols, exit_row, exit_col)
        @n_rows = n_rows
        @n_cols = n_cols
        @exit_row = exit_row
        @exit_col = exit_col
        
        @player_square = Array.new(n_rows) { Array.new(n_cols) }
        @monster_square = Array.new(n_rows) { Array.new(n_cols) }
        @labyrinth_square = Array.new(n_rows) { Array.new(n_cols) }

        @players = Array.new
        @monsters = Array.new
    end

    def spread_players(players)
    end

    def have_a_winner
        return (@player_square[@exit_row][@exit_col] != nil)
    end

    def to_s
        str = ""
        for row in 0...@n_rows
            for col in 0...@n_cols
                str += @labyrinth_square[row][col].get_content() if @labyrinth_square[row][col]
                str += @player_square[row][col].get_name if @player_square[row][col]
                str += @monster_square[row][col].to_s if @monster_square[row][col]
            end
            str += "\n"
        end
        return str
    end

    def add_monster(row, col, monster)
        if pos_ok(row, col) && empty_pos(row, col)
            @labyrinth_square[row][col] = LabyrinthSquare.new(row, col, @@MONSTER_CHAR, self)
            @monster_square[row][col] = MonsterSquare.new(row, col, monster)
            @monsters.push(monster)
            monster.set_pos(row, col)
        end
    end

    def put_player(direction, player)
    end

    def add_block(orientation, start_row, start_col, length)
    end

    def valid_moves(row, col)
    end

    private

    def pos_ok(row, col)
        return (row >= 0 && row < @n_rows && col >= 0 && col < @n_cols)
    end

    def empty_pos(row, col)
        return (@player_square[row][col] == nil) && (@labyrinth_square[row][col].get_content() == @@EMPTY_CHAR)
    end

    def monster_pos(row, col)
        return (@monster_square[row][col] != nil) && (@player_square[row][col] == nil)
    end

    def exit_pos(row, col)
        return (row == @exit_row) && (col == @exit_col)
    end

    def combat_pos(row, col)
        return (@monster_square[row][col] == nil) && (@player_square[row][col] == nil)
    end

    def can_step_on(row, col)
        return (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col)) && pos_ok(row, col)
    end

    def update_old_pos(row, col)
        if pos_ok(row, col)
            if monster_pos(row, col)
                @labyrinth_square[row][col] = @@MONSTER_CHAR
            elsif exit_pos(row, col)
                @labyrinth_square[row][col] = @@EXIT_CHAR
            elsif combat_pos(row, col)
                @labyrinth_square[row][col] = @@COMBAT_CHAR
            elsif empty_pos(row, col)
                @labyrinth_square[row][col] = @@EMPTY_CHAR
            end
        end
    end

    def dir_2_pos(row, col, direction)
        new_pos = [row, col]
        h = 0
        v = 0

        case direction
        when :UP
            h = -1
        when :DOWN
            h = 1
        when :LEFT
            v = -1
        when :RIGHT
            v = 1
        end

        while can_step_on(new_pos[0] + h, new_pos[1] + v)
            new_pos[0] += h
            new_pos[1] += v
        end

        return new_pos
    end

    def random_empty_pos
        found = false
        pos = [0, 0]
        dice = Dice.new
        until found
            pos[0] = dice.random_pos(@n_rows)
            pos[1] = dice.random_pos(@n_cols)
            found = empty_pos(pos[0], pos[1])
        end
        return pos
    end

    def put_player_2d(old_row, old_col, row, col)
    end
end