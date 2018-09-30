package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

//Class is Immutable
public abstract class Tile {
	
	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile>EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i =0; i < 64; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	//Abstract Method returns whether there is a Piece on a Tile
	public abstract boolean isTileOccupied();
	
	//Abstract Method returns the Piece Object that is on a Tile
	public abstract Piece getPiece();
	
	//Tile with no piece on it.
	public static final class EmptyTile extends Tile {
		
		private EmptyTile(final int coordinate) {
			super(coordinate);
		}
		
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	//Tile with a Piece on it.
	public static final class OccupiedTile extends Tile {
		
		private final Piece pieceOnTile;
		
		private OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}

}
