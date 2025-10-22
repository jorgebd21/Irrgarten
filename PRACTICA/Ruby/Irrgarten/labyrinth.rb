class Laberinth
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
        
        @player_square = Array.new(Array.new(n_cols), n_rows)
        @monster_square = Array.new(Array.new(n_cols), n_rows)
        @labyrinth_square = Array.new(Array.new(n_cols), n_rows)

        players = Array.new
        monsters = Array.new
    end

    def spread_players(players)
    end

    def have_a_winner
        return (player_square[@exit_row][@exit_col] != nil)
    end

    def to_s
        str = ""
        for row in 0...@n_rows
            for col in 0...@n_cols
                str += @labyrinth_square[row][col]
                str += @player_square[row][col] unless @player_square[row][col] == nil
                str += @monster_square[row][col] unless @monster_square[row][col] == nil
            end
            str += "\n"
        end
        return str
    end

    def add_monster(row, col, monster)
        if(pos_ok(row, col) && empty_pos(row, col))
            labyrinth_square[row][col] = new labyrinth_square(row, col, @@MONSTER_CHAR)
            monster_square[row][col] = new monster_square(row, col, monster)
            monsters.push(monster)
            monster.set_pos(row, col)
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
        return (@player_square[row][col] == nil) && (@labyrinth_square[row][col].getContent() == @@EMPTY_CHAR)
    end

    def monster_pos(row, col)
        return (@monster_square[row][col] != nil) && (@player_square[row][col] == nil)
    end

    def exit_pos(row, col)
        return (row == @exit_row) && (col == @exit_col)
    end

    def combat_pos(row, col)
        rreturn (@monster_square[row][col] == nil) && (@player_square[row][col] == nil)
    end

    def can_step_on(row, col)
        return (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col)) && pos_ok(row, col)
    end

    def update_old_pos(row, col)
        if(pos_ok(row, col))
            if(monster_pos(row, col))
                @labyrinth_square[row][col] = @@MONSTER_CHAR
            end
            if(exit_pos(row, col))
                @labyrinth_square[row][col] = @@EXIT_CHAR
            end
            if(combat_pos(row, col))
                @labyrinth_square[row][col] = @@COMBAT_CHAR
            end
            if(empty_pos(row, col))
                @labyrinth_square[row][col] = @@EMPTY_CHAR
            end
        end
    end

    def dir_2_pos(row, col, direction)
        new_pos = Array.new(2)
        H=0
        V=0
        new_pos[0] = row
        new_pos[1] = col

        case direction
        when UP
            H = -1
            break
        when DOWN
            H = 1
        when LEFT
            V = -1
            break
        when RIGHT
            V = 1
            break
        end

        while can_step_on(new_pos[0] + H, new_pos[1] + V)
            new_pos[0] += H
            new_pos[1] += V
        end

        return new_pos
    end

    def random_empty_pos
        found = false
        pos = Array.new(2)
        dice = new Dice()
        do
            pos[0] = dice.random_pos(@n_rows)
            pos[1] = dice.random_pos(@n_cols)
            if(empty_pos(pos[0], pos[1]))
                found = true
            end
        while(!found)
        return pos
    end 

    def put_player_2d(old_row, old_col, row, col)
    end
end