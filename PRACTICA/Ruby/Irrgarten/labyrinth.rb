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
        
        @players = Array.new(n_rows) { Array.new(n_cols) }
        @monsters = Array.new(n_rows) { Array.new(n_cols) }
        @labyrinth = Array.new(n_rows) { Array.new(n_cols) }

        for i in n_rows
            for j in n_cols
                @labyrinth[i][j] = Labyrinth_square.new(i, j, @@EMPTY_CHAR)
            end
        end
        @labyrinth[exit_row][exit_col] = Labyrinth_square.new(exit_row, exit_col, @@EXIT_CHAR)
    end

    def spread_players(player)
        for i in 0...player.length
            pos = random_empty_pos()
            put_player_2d(-1, -1, pos[0], pos[1], player[i])
        end
    end

    def have_a_winner
        return (@players[@exit_row][@exit_col] != nil)
    end

    def to_s
        str = ""
        for row in 0...@n_rows
            for col in 0...@n_cols
                if(@players[row][col] != nil)
                    str += "  " + @players[row][col].to_s() + "  "
                else
                    str += "  " + @labyrinth[row][col].get() + "  "
                end
            end
            str += "\n"
        end
        return str
    end

    def add_monster(row, col, monster)
        if pos_ok(row, col) && empty_pos(row, col)
            @labyrinth[row][col] = LabyrinthSquare.new(row, col, @@MONSTER_CHAR)
            @monsters[row][col] = MonsterSquare.new(row, col, monster)
            @monsters[row][col].get().set_pos(row, col)
        end
    end

    def put_player(direction, player)
        old_row = player.get_row()
        old_col = player.get_col()
        new_pos = dir_2_pos(old_row, old_col, direction)
        return put_player_2d(old_row, old_col, new_pos[0], new_pos[1], player)
    end

    def add_block(orientation, start_row, start_col, length)
        if(orientation == Orientation.VERTICAL)
            inc_row = 1
            inc_col = 0
        else
            inc_row = 0
            inc_col = 1
        end

        row = start_row
        col = start_col

        while(pos_ok(row, col) && (empty_pos(row, col) && (length > 0)))
            @labyrinth[row][col].set(row, col, @@BLOCK_CHAR)
            length -= 1
            row += inc_row
            col += inc_col
        end
    end

    def valid_moves(row, col)
        output = Array.new()
        if(can_step_on(row - 1, col))
            output.push(Direction.UP)
        end
        if(can_step_on(row + 1, col))
            output.push(Direction.DOWN)
        end
        if(can_step_on(row, col - 1))
            output.push(Direction.LEFT)
        end
        if(can_step_on(row, col + 1))
            output.push(Direction.RIGHT)
        end
        return output
    end

    private

    def pos_ok(row, col)
        return ((row >= 0) && (row < @n_rows) && (col >= 0) && (col < @n_cols))
    end

    def empty_pos(row, col)
        return (@players[row][col] == nil) && (@labyrinth[row][col].get() == @@EMPTY_CHAR)
    end

    def monster_pos(row, col)
        return (@monsters[row][col] != nil) && (@players[row][col] == nil)
    end

    def exit_pos(row, col)
        return (row == @exit_row) && (col == @exit_col)
    end

    def combat_pos(row, col)
        return (@monsters[row][col] == nil) && (@players[row][col] == nil)
    end

    def can_step_on(row, col)
        if(!pos_ok(row, col))
            return false
        return (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col))
    end

    def update_old_pos(row, col)
        if pos_ok(row, col)
            if monster_pos(row, col)
                @labyrinth[row][col].set(row, col, @@MONSTER_CHAR)
            elsif exit_pos(row, col)
                @labyrinth[row][col].set(row, col, @@EXIT_CHAR)
            elsif combat_pos(row, col)
                @labyrinth[row][col].set(row, col, @@COMBAT_CHAR)
            elsif empty_pos(row, col)
                @labyrinth[row][col].set(row, col, @@EMPTY_CHAR)
            end
        end
    end

    def dir_2_pos(row, col, direction)
        new_pos = [row, col]
        h = 0
        v = 0

        case direction
        when :up
            h = -1
        when :down
            h = 1
        when :left
            v = -1
        when :right
            v = 1
        end

        next_row = new_pos[0] + h
        next_col = new_pos[1] + v

        if (pos_ok(next_row,next_col) && can_step_on(next_row, next_col))
            new_pos[0] = next_row
            new_pos[1] = next_col
        end

        return new_pos
    end

    def random_empty_pos()
        found = false
        pos = [0, 0]
        dice = Dice.new
        until found
            pos[0] = dice.random_pos(@n_rows)
            pos[1] = dice.random_pos(@n_cols)
            if(empty_pos(pos[0], pos[1]))
                found = true
            end
        end
        return pos
    end

    def put_player_2d(old_row, old_col, row, col, player)
        output = nil
        if(can_step_on(old_row, old_col))
            if(pos_ok(old_row, old_col))
                p = @players[old_row][old_col].get()
                if (p == player)
                    @players[old_row][old_col] = nil
                    update_old_pos(old_row, old_col)
                end
            end

            monster_pos = monster_pos(row, col)
            if(monster_pos)
                @labyrinth[row][col].set(row, col, @@COMBAT_CHAR)
                output = @monsters[row][col].get()
            else
                number = player.get_number()
                @labyrinth[row][col].set(row, col, number)
            end

            @players[row][col] = Player_square.new(row, col, player)
            player.set_pos(row, col)
        end
        
        return output
    end
end