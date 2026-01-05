require_relative 'controller/controller'
require_relative 'UI/textUI'
require_relative 'game'

module Irrgarten
  class Main
    def self.run
      controller = Control::Controller.new(Game.new(2), UI::TextUI.new)
      controller.play()
    end
  end
end

Irrgarten::Main.run