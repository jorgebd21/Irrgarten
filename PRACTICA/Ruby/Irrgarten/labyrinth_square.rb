class Labyrinth_square
    def initialize(row, col, content)
        @row = row
        @col = col
        @content = content
    end

    def get_content()
        @content
    end

    def set_content(content)
        @content = content
    end
end