package Patrones;

public interface StrategySnakeAttack {
	public float validarLadoSerpenteo(boolean stateSerpenteo);
	
	public float actualizarVelAcum(float velAcum);
	
	public boolean validarVelAcum(float vel, float velAcum);
	
	public int validarAleatoriaDir(int dir);
}
