package Patrones;

public class MediumSnakeAttack implements StrategySnakeAttack {
	
	@Override
	public float validarLadoSerpenteo(boolean stateSerpenteo) {
		float ladoSerpenteo;
		float serpenteo = .2f;
		if(stateSerpenteo)
			ladoSerpenteo = serpenteo;
		else 
			ladoSerpenteo = (-1) * serpenteo;
		stateSerpenteo = !stateSerpenteo;
		return ladoSerpenteo;
	}

	@Override
	public boolean validarVelAcum(float vel, float velAcum) {
		if((int)(velAcum/35) == 1) {
			return true;
		}
		return false;
	}
	
	public int getDirAleatoria(int dir) {
		int dirAleatoria;
		while(true) {
			dirAleatoria = (int) Math.floor(Math.random()*(2+2+1)-2); //Número aleatorio entre -2 y 2
			if(dirAleatoria != dir && dirAleatoria != (-1)*dir && dirAleatoria != 0) {
				return dirAleatoria;
			}
		}
	}
	
	@Override
	public int validarAleatoriaDir(int dir) {
		int numAleatorio = (int) (Math.random()*10000 + 1); //Número aleatorio entre 1 y 10000
		if(numAleatorio <= 200) {
			return getDirAleatoria(dir);
		}
		return dir;
	}

	@Override
	public float actualizarVelAcum(float velAcum) {
		return velAcum - 35;
	}
}
