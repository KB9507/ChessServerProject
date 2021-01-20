package com.kb.chess.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kb.chess.server.GameStarter.PieceColor;


@RestController
@RequestMapping(path="/matchmaking", produces="application/json", consumes="application/json")
public class MatchmakerController {

	@PostMapping()
	public ResponseEntity<GameStarter> matchmake() {
		//findOpponent();
		return new ResponseEntity<GameStarter>(new GameStarter(1,PieceColor.WHITE), HttpStatus.ACCEPTED);
	}
}
