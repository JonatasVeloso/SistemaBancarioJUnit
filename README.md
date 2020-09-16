# SistemaBancarioJUnit
Projeto criado para aperfeiçoar o conhecimento em JUnit

Padrão:
Sempre crie uma classe de teste com o nome da classe à ser testada + “Test”.
Exemplo Classe: Cliente.java, Classe Teste: ClienteTest.java
Nos métodos, inicie com “test”
Exemplo: testConsultaCliente

Estrutura do Teste:
Cenário: Instanciar objetos e preencher valores.
Processo: Fazer o objeto passar por alguma ação, parte da execução.
Verificação: Validar os resultados.

Boas Práticas:
Testar os limites.

Comandos:
@Test: Indica que aquele método é um método de Teste.
assert: Esse grupo de funções asser, são utilizados para a validação. 
fail(); Caso caia nesta linha, o teste falhou. Exemplo: usado quando é necessário cair no catch, mas não cai.

Cenário: 
@Before: Caso o cenário se repita, criar um método chamado setUp para construir o cenário com a notação Before.
@After: Caso tenha um método Before, criar um método chamado tearDown, para limpar as variáveis com a notação After.

Verificações:
assertThat(gerClientes.getClientesDoBanco().size(), is(1)); Verifica se o primeiro valor é igual ao segundo.
assertNull(gerClientes.pesquisaCliente(2)); verifica se o primeiro valor é nulo
assertTrue(boolean); Verifica se o valor é verdadeiro
asserFalse(boolean); Verifica se o valor é falso

Suite de Testes:
Necessário para executar todos os testes.
@RunWith(Suite.class)
@SuiteClasses({ SuaClasseTest.class, OutraClasseTest.class})

Documentação:
Comentários na Classe
Comentários nos Métodos
@autor
@date

