package negocio;

/**
 * Exce��o a ser lan�ada quando a idade de um poss�vel novo cliente n�o for aceita.
 * 
 * @author Jonatas Veloso Siqueira
 * @date 16/09/2020 
 */
public class IdadeNaoPermitidaException extends Exception {

	public static String MSG_IDADE_INVALIDA = "A idade do cliente precisa estar entre 18 e 65 anos.";
	
	public IdadeNaoPermitidaException(String msg) {
		super(msg);
	}

}
