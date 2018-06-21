package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;

public class BoardTest {

	@Test
	public void ganaPrimerJugador() {
		
		Board tablero = new Board();
		
		// O | O | X
		//   | X | O
		// X |   | X	
		  
		Cell celda0 = tablero.getCell(4);
		celda0.value = "X";
		Cell celda1 = tablero.getCell(1);
		celda1.value = "O";
		Cell celda2 = tablero.getCell(8);
		celda2.value = "X";
		Cell celda3 = tablero.getCell(0);
		celda3.value = "O";
		Cell celda4 = tablero.getCell(2);
		celda4.value = "X";
		Cell celda5 = tablero.getCell(5);
		celda5.value = "O";
		Cell celda6 = tablero.getCell(6);
		celda6.value = "X";
		  
		int[] victoria = { 6, 4, 2 };
		int[] lineaGanadora = tablero.getCellsIfWinner("X");
		   
		assertEquals(true, Arrays.equals(victoria, lineaGanadora));
		assertEquals(false, tablero.checkDraw());


	}
	
	@Test
	public void pierdePrimerJugador() {
		
		Board tablero = new Board();
		  
		// O |   | X
		// O | X | 
		// O | X | 	
		
		Cell celda0 = tablero.getCell(2);
		celda0.value = "X";
		Cell celda1 = tablero.getCell(0);
		celda1.value = "O";
		Cell celda2 = tablero.getCell(4);
		celda2.value = "X";
		Cell celda3 = tablero.getCell(6);
		celda3.value = "O";
		Cell celda4 = tablero.getCell(7);
		celda4.value = "X";
		Cell celda5 = tablero.getCell(3);
		celda5.value = "O";
		  
		int[] victoria = { 0, 3, 6 };
		int[] lineaGanadora = tablero.getCellsIfWinner("O");
		   
		assertEquals(true, Arrays.equals(victoria, lineaGanadora));
		assertEquals(false, tablero.checkDraw());


	}
	
	
	@Test
	public void hayEmpate() {
		
		Board tablero = new Board();
		  
		// X | O | O
		// O | X | X
		// X | X | O		
				
		Cell celda0 = tablero.getCell(0);
		celda0.value = "X";
		Cell celda1 = tablero.getCell(3);
		celda1.value = "O";
		Cell celda2 = tablero.getCell(4);
		celda2.value = "X";
		Cell celda3 = tablero.getCell(8);
		celda3.value = "O";
		Cell celda4 = tablero.getCell(6);
		celda4.value = "X";
		Cell celda5 = tablero.getCell(2);
		celda5.value = "O";
		Cell celda6 = tablero.getCell(5);
		celda6.value = "X";
		Cell celda7 = tablero.getCell(1);
		celda7.value = "O";
		Cell celda8 = tablero.getCell(7);
		celda8.value = "X";
		  
		assertEquals(true, tablero.checkDraw());

	}	

}
