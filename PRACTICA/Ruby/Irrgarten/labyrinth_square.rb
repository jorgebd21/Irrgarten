class Labyrinth_square
    def initialize(row, col, content, labyrinth)
        @row = row
        @col = col
        @content = content
        @labyrinth = labyrinth
    end

    def get_content()
        @content
    end

    def set_content(content)
        @content = content
    end
end