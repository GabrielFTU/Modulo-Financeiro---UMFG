package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.DatabaseConnection;
import model.Evento;
import model.Inscricao;
import model.Lote;
import model.Pagamento;
import model.Pagamento.StatusPagamento;

public class InscricaoRepository {

	public void salvar(Inscricao inscricao) {
		String sqlInscricao = "INSERT INTO inscricao (nome_inscrito, lote_id, data_inscricao, tipo_ingresso, motivo_cortesia, cancelado) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
		String sqlPagamento = "INSERT INTO pagamento (inscricao_id, valor_pago, forma_pagamento, status_pagamento, comprovante_url) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);

			int inscricaoId;

			try (PreparedStatement stmtInscricao = conn.prepareStatement(sqlInscricao)) {
				stmtInscricao.setString(1, inscricao.getNomeInscrito());
				stmtInscricao.setInt(2, inscricao.getLote().getId());
				stmtInscricao.setObject(3, inscricao.getDataInscricao());
				stmtInscricao.setString(4, inscricao.getTipoIngresso());
				stmtInscricao.setString(5, inscricao.getMotivoCortesia());
				stmtInscricao.setBoolean(6, inscricao.isCancelado());

				ResultSet rs = stmtInscricao.executeQuery();
				if (rs.next()) {
					inscricaoId = rs.getInt(1);
				} else {
					throw new SQLException("Falha ao criar inscrição, nenhum ID obtido.");
				}
			}

			try (PreparedStatement stmtPagamento = conn.prepareStatement(sqlPagamento)) {
				Pagamento pagamento = inscricao.getPagamento();
				stmtPagamento.setInt(1, inscricaoId); 
				stmtPagamento.setDouble(2, pagamento.getValorPago());
				stmtPagamento.setString(3, pagamento.getFormaPagamento());
				stmtPagamento.setString(4, pagamento.getStatusPagamento().name());
				stmtPagamento.setString(5, pagamento.getComprovanteUrl());
				
				stmtPagamento.executeUpdate();
			}
			
			conn.commit();

		} catch (SQLException e) {
			System.err.println("Erro ao salvar inscrição, revertendo a transação.");
			e.printStackTrace();
	
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					System.err.println("Erro ao tentar reverter a transação.");
					ex.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Inscricao> listarInscricoes() {
		List<Inscricao> inscricoes = new ArrayList<>();
		
		String sql = "SELECT " +
					 "i.id as inscricao_id, i.nome_inscrito, i.data_inscricao, i.tipo_ingresso, i.motivo_cortesia, i.cancelado, " +
					 "l.id as lote_id, l.nome as lote_nome, l.valor as lote_valor, " +
					 "e.id as evento_id, e.nome as evento_nome, " +
					 "p.id as pagamento_id, p.valor_pago, p.forma_pagamento, p.status_pagamento, p.comprovante_url " +
					 "FROM inscricao i " +
					 "JOIN lote l ON i.lote_id = l.id " +
					 "JOIN evento e ON l.evento_id = e.id " +
					 "JOIN pagamento p ON i.id = p.inscricao_id " +
					 "ORDER BY i.id";

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Evento evento = new Evento(
					rs.getInt("evento_id"),
					rs.getString("evento_nome")
				);

				Lote lote = new Lote(
					rs.getInt("lote_id"),
					rs.getString("lote_nome"),
					evento,
					rs.getDouble("lote_valor")
				);

				Pagamento pagamento = new Pagamento(
					rs.getInt("pagamento_id"),
					rs.getDouble("valor_pago"),
					rs.getString("forma_pagamento"),
					StatusPagamento.valueOf(rs.getString("status_pagamento"))
				);
				pagamento.setComprovanteUrl(rs.getString("comprovante_url"));

		
				Inscricao inscricao = new Inscricao(
					rs.getInt("inscricao_id"),
					rs.getString("nome_inscrito"),
					lote,
					rs.getObject("data_inscricao", LocalDate.class)
				);
				inscricao.setTipoIngresso(rs.getString("tipo_ingresso"));
				inscricao.setMotivoCortesia(rs.getString("motivo_cortesia"));
				inscricao.setCancelado(rs.getBoolean("cancelado"));
				inscricao.setPagamento(pagamento);
				inscricoes.add(inscricao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inscricoes;
	}
}