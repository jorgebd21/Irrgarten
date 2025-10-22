class Labyrinth_square
    def initialize(row, col, content, labyrinth)
        @row = row
        @col = col
        @content = content
        @labyrinth = labyrinth
    end

    def getContent()
        @content
    end

    def setContent(content)
        @content = content
    end
end