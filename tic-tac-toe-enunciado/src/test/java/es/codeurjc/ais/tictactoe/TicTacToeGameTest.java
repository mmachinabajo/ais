package es.codeurjc.ais.tictactoe;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.reset;

import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

class TicTacToeGameTest {
	
	TicTacToeGame juego;
	Connection navegador1;
	Connection navegador2;
	Player jugador1;
	Player jugador2;
	
	@BeforeEach
	public void inicializacion() {
		//Crear el objeto TicTacToeGame
		juego = new TicTacToeGame();
	
		//Crear los dobles de los objetos Connection
		navegador1 = mock(Connection.class);
		navegador2 = mock(Connection.class);
	
		//Añadir los dobles al objeto TicTacToeGame
		juego.addConnection(navegador1);
		juego.addConnection(navegador2);
	
		//Crear los dos jugadores
		jugador1 = new Player(0, "X", "Miguel");
		jugador2 = new Player(1, "O", "Pedro");
	
		//Añadir los jugadores al objeto TicTacToeGame
		juego.addPlayer(jugador1);
		reset(navegador1);
		reset(navegador2);
		juego.addPlayer(jugador2);
	}


	@Test
	void ganaPrimerJugador() {
		
		// O | O | X
		//   | X | O
		// X |   | X	
		
		//Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador1).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Comprobar que la conexión 2 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador2).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Por turnos, cada jugador va marcando una casilla invocando el método mark
		//Empieza el jugador1
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(4);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(1);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(8);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(0);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(2);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(5);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(6);
		
		//Al final se comprueba que el juego acaba y que gana el jugador1
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(navegador1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue evento1 = argument.getValue();
		verify(navegador2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue evento2 = argument.getValue();
		
		assertThat(evento1.player).isEqualTo(jugador1);
		assertThat(evento2.player).isEqualTo(jugador1);
		
	}
	
	@Test
	void pierdePrimerJugador() {
		
		// O |   | X
		// O | X | 
		// O | X | 	
	
		//Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador1).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Comprobar que la conexión 2 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador2).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Por turnos, cada jugador va marcando una casilla invocando el método mark
		//Empieza el jugador1
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(2);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(0);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(4);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(6);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(7);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(3);
		
		//Al final se comprueba que el juego acaba y que gana el jugador2
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(navegador1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue evento1 = argument.getValue();
		verify(navegador2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue evento2 = argument.getValue();
		
		assertThat(evento1.player).isEqualTo(jugador2);
		assertThat(evento2.player).isEqualTo(jugador2);
		
	}
	
	@Test
	void hayEmpate() {
		
		// X | O | O
		// O | X | X
		// X | X | O	
		
		//Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador1).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Comprobar que la conexión 2 recibe el evento JOIN_GAME, con ambos jugadores		
		verify(navegador2).sendEvent(EventType.JOIN_GAME, juego.getPlayers());
		
		//Por turnos, cada jugador va marcando una casilla invocando el método mark
		//Empieza el jugador1
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(0);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(3);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(4);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(8);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(6);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(2);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(5);
		assertThat(juego.checkTurn(1)).isTrue();
		juego.mark(1);
		assertThat(juego.checkTurn(0)).isTrue();
		juego.mark(7);
		
		verify(navegador1).sendEvent(EventType.GAME_OVER, null);
		verify(navegador2).sendEvent(EventType.GAME_OVER, null);
		
	}
}
	
