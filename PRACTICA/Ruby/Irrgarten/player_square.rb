class Player_square
    def initialize(row, col, name)
        @row = row
        @col = col
        @name = name
    end

    def get()
        return @name
    end

    def set(row, col, name)
        @row = row
        @col = col
        @name = name
    end
    
    def to_s()
        return @name.get_number() + ""
    end
end