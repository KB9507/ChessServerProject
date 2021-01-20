package com.kb.chess.server;

public class GameStarter {
	
	private final int room_id;
	private final PieceColor color;
	
	public GameStarter(int room_id, PieceColor color) {
		super();
		this.room_id = room_id;
		this.color = color;
	}

	public int getRoom_id() {
		return room_id;
	}
	
	public PieceColor getColor() {
		return color;
	}

	public enum PieceColor {
		WHITE,
		BLACK;
	}
}
