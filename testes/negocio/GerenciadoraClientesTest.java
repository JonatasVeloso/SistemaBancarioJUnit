package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Jonatas Veloso Siqueira
 * @date 16/09/2020 
 */
public class GerenciadoraClientesTest {

	private GerenciadoraClientes gerClientes;
	private int idCLiente01 = 1;
	private	int idCLiente02 = 2;
	
	@Before
	public void setUp() {
	
		/* ========== Montagem do cenário ========== */
		
		// criando alguns clientes
		Cliente cliente01 = new Cliente(idCLiente01, "Jonatas Veloso", 23, "jntsvls@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Gustavo Lopes", 25, "gulopes@gmail.com", 1, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}
	
	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);
		
		/* ========== Verificações ========== */
		assertThat(cliente.getId(), is(idCLiente01));
		
	}
	
	/**
	 * Teste básico da pesquisa por um cliente que não existe.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 */
	@Test
	public void testPesquisaClienteInexistente() {

		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1001);
		
		/* ========== Verificações ========== */
		assertNull(cliente);
		
	}
	
	/**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 */
	@Test
	public void testRemoveCliente() {
		
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);
		
		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));
		
	}
	
	/**
	 * Teste da tentativa de remoção de um cliente inexistente.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 */
	@Test
	public void testRemoveClienteInexistente() {

	
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(1001);
		
		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
		
	}
	
	/**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 * @throws IdadeNaoPermitidaException
	 */
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */		
		Cliente cliente = new Cliente(1, "Jonatas", 23, "jntsvls@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verificações ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 * @throws IdadeNaoPermitidaException 
	 */
	@Test
	public void testClienteIdadeAceitavel_02() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */		
		Cliente cliente = new Cliente(1, "Jonatas", 18, "jntsvls@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verificações ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 * @throws IdadeNaoPermitidaException 
	 */
	@Test
	public void testClienteIdadeAceitavel_03() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */		
		Cliente cliente = new Cliente(1, "Jonatas", 65, "jntsvls@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verificações ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Validação da idade de um cliente quando a mesma está abaixo intervalo permitido.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 * @throws IdadeNaoPermitidaException 
	 */
	@Test
	public void testClienteIdadeAceitavel_04() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenário ========== */		
		Cliente cliente = new Cliente(1, "Jonatas", 17, "jntsvls@gmail.com", 1, true);

		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}	
	}
	
	/**
	 * Validação da idade de um cliente quando a mesma está acima intervalo permitido.
	 * 
	 * @author Jonatas Veloso Siqueira
	 * @date 16/09/2020 
	 * @throws IdadeNaoPermitidaException
	 */
	@Test
	public void testClienteIdadeAceitavel_05() throws IdadeNaoPermitidaException {
		
		/* ========== Montagem do Cenário ========== */		
		Cliente cliente = new Cliente(1, "Jonatas", 66, "jntsvls@gmail.com", 1, true);
		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}	
	}
	
}