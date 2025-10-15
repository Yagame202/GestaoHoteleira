package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.ClienteException;
import core.model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO implements DAO<Cliente> {

    public ArrayList<Cliente> selecionar() throws ClienteException {
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento, " +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade, " +
                    "fidelidade, " +
                    "observacao " +
                    "FROM cliente";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Cliente> clientes = new ArrayList<>();
            while (resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Cliente cliente) throws ClienteException {
        try {
            String sql = "INSERT INTO cliente " +
                    "(nome_completo, data_nascimento, documento, pais, estado, cidade, fidelidade, observacao) " +
                    "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cliente.getNomeCompleto());
            preparacao.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            preparacao.setString(3, cliente.getDocumento());
            preparacao.setString(4, cliente.getPais());
            preparacao.setString(5, cliente.getEstado());
            preparacao.setString(6, cliente.getCidade());
            preparacao.setBoolean(7, cliente.getFidelidade());
            preparacao.setString(8, cliente.getObservacao());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Cliente cliente) throws ClienteException {
        try {
            String sql = "UPDATE cliente " +
                    "SET " +
                    "nome_completo = ?, " +
                    "data_nascimento = ?, " +
                    "documento = ?, " +
                    "pais = ?, " +
                    "estado = ?, " +
                    "cidade = ?, " +
                    "fidelidade = ?, " +
                    "observacao = ? " +
                    "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cliente.getNomeCompleto());
            preparacao.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            preparacao.setString(3, cliente.getDocumento());
            preparacao.setString(4, cliente.getPais());
            preparacao.setString(5, cliente.getEstado());
            preparacao.setString(6, cliente.getCidade());
            preparacao.setBoolean(7, cliente.getFidelidade());
            preparacao.setString(8, cliente.getObservacao());
            preparacao.setLong(9, cliente.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean deletar(Long id) throws ClienteException {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Cliente selecionarPorId(Long id) throws ClienteException {
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento, " +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade, " +
                    "fidelidade, " +
                    "observacao " +
                    "FROM cliente " +
                    "WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()) {
                return new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Cliente selecionarUltima() throws ClienteException {
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento, " +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade, " +
                    "fidelidade, " +
                    "observacao " +
                    "FROM cliente " +
                    "ORDER BY id DESC " +
                    "LIMIT 1";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            if (resultado.next()) {
                return new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Cliente selecionarPorDocumento(String documento) throws ClienteException {
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento, " +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade, " +
                    "fidelidade, " +
                    "observacao " +
                    "FROM cliente " +
                    "WHERE documento = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, documento);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()) {
                return new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
}