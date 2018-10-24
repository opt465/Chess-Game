import java.util.LinkedList;
import java.util.List;

public class King extends Piece{
	
	private String color;
	
	public King(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	public List<BoardCoordinate> moves(int x, int y){
		
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		
		int[] X = { 0, 0, 1, -1, 1, -1, 1, -1};
		int[] Y = { 1, -1, 0, 0, 1, 1, -1, -1};
		
		int xPos = x;
		int yPos = y;
		
		for(int i = 0; i < 8; i++) {
			coordinates.add(new BoardCoordinate(xPos+=X[i],yPos+=Y[i]));
		}
		
		
		return coordinates ;		
	}

	@Override
	public String getName() {
		return "king";
	}

}
