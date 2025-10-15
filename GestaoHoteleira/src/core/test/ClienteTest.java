package core.test;

import base.enumeration.Funcionalidade;
import base.exception.ClienteException;
import base.exception.PessoaException;
import core.model.Cliente;
import core.service.ClienteService;
import core.service.PessoaService;

public class ClienteTest {  // Atributos
    private ClienteService clienteService;

    // Construtor
    public ClienteTest(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Métodos de testes

    public String testar(Funcionalidade funcionalidade) throws ClienteException {
        switch (funcionalidade) {
            case LISTAR:
                return this.listar();
            case CADASTRAR:
                return this.cadastrar();
            case ALTERAR:
                return this.alterar();
            case EXCLUIR:
                return this.excluir();
            default:
                return null;
        }
    }

    public String listar() throws ClienteException {
        return clienteService.listar();
    }

    public String cadastrar() throws ClienteException {
        // Dados para cadastro
        String nomeCompleto = "João Luiz da Silva";
        String dataNascimento = "01/02/1990";
        String documento = "121.341.560-82";
        String pais = "Brasil";
        String estado = "SC";
        String cidade = "Tubarão";
        String fidelidade = "1";
        String observacao = "Calmo e respeitoso";

        return ClienteService.cadastrar(nomeCompleto, dataNascimento, documento, pais, estado, cidade. fidelidade, observacao);
    }

    public String alterar() throws ClienteException {
        // Dados para alteração
        String id = "1";
        String nomeCompleto = "João Luiz da Silva Pereira";
        String dataNascimento = "01/02/1989";
        String documento = "670.022.930-87";
        String pais = "Argentina";
        String estado = "Santa Fe";
        String cidade = "Rosário";
        String fidelidade = "0";
        String observacao = "Doido";

        return ClienteService.(id, nomeCompleto, dataNascimento, documento, pais, estado, cidade, fidelidade, observacao);
    }

    public String excluir() throws ClienteException {
        // Dados para exclusão
        String id = "10";

        return ClienteService.excluir(id);
    }
}
