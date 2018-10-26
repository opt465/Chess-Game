import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Validator {

	ChessBoard board;

	public Validator(ChessBoard board) {
		this.board = board;
	}

	public boolean isSameColor(Piece p1, Piece p2) {
		if(p1.getColor().equals(p2.getColor()))
			return true;
		return false;
	}
	
	public List<BoardCoordinate> calculateValidMoves(Piece piece) throws Exception{
		throw new Exception("You are not supposed to be here.");
	}

	public List<BoardCoordinate> calculateValidMoves(Pawn pawn){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		BoardCoordinate coor = pawn.getCoordinate();

		int xPos = coor.getX();
		int yPos = coor.getY();

		int[] X = {0, 0, 1, -1};
		int[] Y = {2, 1, 1, 1};

		if(pawn.getFirstMove()) {
			pawn.setFirstMove(false);

			for(int i = 0; i < X.length; i++) {
				coordinates.add(new BoardCoordinate(xPos+=X[i], yPos+=Y[i]));
			}

			return coordinates;	
		}
		else {

			for(int i = 1; i < X.length; i++) {
				coordinates.add(new BoardCoordinate(xPos+=X[i], yPos+=Y[i]));
			}

			return coordinates;
		}

	}

	public List<BoardCoordinate> calculateValidMoves(Rook rook){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		BoardCoordinate coor = rook.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();

		verticalMove(coordinates,x,y,1);
		verticalMove(coordinates,x,y,-1);
		horizontalMove(coordinates,x,y,1);
		horizontalMove(coordinates,x,y,-1);

		return coordinates;	

	}

	public List<BoardCoordinate> calculateValidMoves(Knight knight){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		BoardCoordinate coor = knight.getCoordinate();

		int xPos = coor.getX();
		int yPos = coor.getY();

		int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
		int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

		for(int i = 0; i < ChessBoard.BOARD_LENGTH; i++) {
			if(board.getPiece(xPos+X[i],yPos+Y[i]) == null || !isSameColor(board.getPiece(xPos, yPos),board.getPiece(xPos+X[i],yPos+Y[i])))
				coordinates.add(new BoardCoordinate(xPos+=X[i],yPos+=Y[i]));
		}


		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Bishop bishop){
		ArrayList<BoardCoordinate>coordinates = new ArrayList<BoardCoordinate>();

		BoardCoordinate coor = bishop.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();
		
		btmDiagonalMove(coordinates, x, y, 1, 1); 
		btmDiagonalMove(coordinates, x, y, -1, -1); 
		topDiagonalMove(coordinates, x, y, -1, 1);
		topDiagonalMove(coordinates, x, y, 1, -1);


		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Queen queen){

		List<BoardCoordinate> coordinates = new LinkedList<>();

		BoardCoordinate coor = queen.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();
		
		
		btmDiagonalMove(coordinates, x, y, 1, 1); 
		btmDiagonalMove(coordinates, x, y, -1, -1); 
		topDiagonalMove(coordinates, x, y, -1, 1);
		topDiagonalMove(coordinates, x, y, 1, -1);

		verticalMove(coordinates,x,y,1, ChessBoard.BOARD_LENGTH);
		verticalMove(coordinates,x,y,-1, 0);
		horizontalMove(coordinates,x,y,1, ChessBoard.BOARD_LENGTH);
		horizontalMove(coordinates,x,y,-1, 0);

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(King king){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		BoardCoordinate coor = king.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();

		int[] X = { 0, 0, 1, -1, 1, -1, 1, -1};
		int[] Y = { 1, -1, 0, 0, 1, 1, -1, -1};

		for(int xPos = x, yPos = y, i = 0; i < 8; i++) {	
			if(board.getPiece(xPos+X[i],yPos+Y[i]) == null || !isSameColor(board.getPiece(xPos, yPos),board.getPiece(xPos+X[i],yPos+Y[i])))
				coordinates.add(new BoardCoordinate(xPos+=X[i],yPos+=Y[i]));
		}


		return coordinates ;	
	}
	
	public boolean isValidMove(LinkedList<BoardCoordinate> validMoves, BoardCoordinate move) {
		for(BoardCoordinate validMove: validMoves) {
			if(move.getX() == validMove.getX() && move.getY() == validMove.getY()) {
				return true;
			}
		}
		return false;
	}
	
	private void verticalMove(List<BoardCoordinate> coordinates, int xPos, int yPos, int dir, int length) {
			for(int i = xPos + dir; i <= length;i+=dir) {
				if(board.getPiece(i,yPos) == null)
					coordinates.add(new BoardCoordinate(i, yPos));
				else if(!isSameColor(board.getPiece(xPos, yPos),board.getPiece(i,yPos))){
					coordinates.add(new BoardCoordinate(i, yPos));
					break;
				}
				else 
					break;
			}
	}
	
	private void horizontalMove(List<BoardCoordinate> coordinates, int xPos, int yPos, int dir, int length) {
			for(int i = yPos + dir; i < length; i=+ dir) {
				if(board.getPiece(xPos,i) == null)
					coordinates.add(new BoardCoordinate(xPos,i));
				else if(!isSameColor(board.getPiece(xPos, yPos),board.getPiece(xPos,i))){
					coordinates.add(new BoardCoordinate(xPos,i));
					break;
				}
				else 
					break;			
			}	
	}
	
	private void topDiagonalMove(List<BoardCoordinate> coordinates, int x, int y, int left, int right) {
		for(int xPos = x, yPos = y; xPos <= 0;) {
			if(board.getPiece(xPos+=left, yPos+=right) == null)
				coordinates.add(new BoardCoordinate(xPos+=left, yPos+=right));
			else if(!isSameColor(board.getPiece(xPos, yPos),board.getPiece(xPos+=left,yPos+=right))){
				coordinates.add(new BoardCoordinate(xPos+=left, yPos+=right));
				break;
			}
			else 
				break;
		}
	}
	
	private void btmDiagonalMove(List<BoardCoordinate> coordinates, int x, int y, int left, int right) {
		for(int xPos = x, yPos = y; xPos < ChessBoard.BOARD_LENGTH;) {
			if(board.getPiece(xPos+=left,yPos+=right) == null)
				coordinates.add(new BoardCoordinate(xPos+=left, yPos+=right));
			else if(!isSameColor(board.getPiece(xPos, yPos),board.getPiece(xPos+=left,yPos+=right))){
				coordinates.add(new BoardCoordinate(xPos+=left, yPos+=right));
				break;
			}
			else 
				break;
			
		}
	}
		
	public boolean underCheck(Player currentPlayer) {
		return false;//King is in validMove of enemy piece?
	}
	
	public boolean underCheckmate(Player currentPlayer) {
		return false;//If currentPlayer's King is under check & no validMoves 
		//for any of player's pieces
	}
	
	public boolean isDraw() {
		return false;//stalemate
		//fifty-move rule
		/*dead position? no sequence of legal moves can lead to checkmate, 
		 * most commonly when neither player has sufficient 
		 * material to checkmate the opponent.
		 */
	}
}
