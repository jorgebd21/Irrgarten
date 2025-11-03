class Labyrinth_square
    def initialize(row, col, content)
        @row = row
        @col = col
        @content = content
    end

    def get()
        @content
    end

    def set(row, col, content)
        @row = row
        @col = col
        @content = content
    end
end