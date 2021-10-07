package sds.cc.controller;

import org.springframework.web.bind.annotation.*;
import sds.cc.model.Game;
import sds.cc.model.GameMap;
import sds.cc.model.Player;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {
    private Game game;

    @PostMapping("new")
    public void newGame() {
        GameMap gameMap = new GameMap(3);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player('X'));
        players.add(new Player('0'));

        game = new Game(gameMap, players);
    }

    @GetMapping("getMapInfo")
    public char[][] getMapInfo() {
        return game.getMapCells();
    }

    @GetMapping("currentPlayer")
    public char getCurrentPlayer() {
        return game.getCurrentPlayer().getSymbol();
    }

    @PostMapping("turn")
    public void doTurn(@RequestBody Map<String, String> data) {
        int row = Integer.parseInt(data.get("row"));
        int col = Integer.parseInt(data.get("col"));

        game.doTurn(row, col);
    }

    @GetMapping("isFinished")
    public boolean isFinished() {
        return game.isFinished();
    }
}
