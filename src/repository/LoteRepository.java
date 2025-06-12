package repository;

import model.Lote;
import model.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseConnection;

public class LoteRepository {

	public void salvar(Lote lote) {
		String sql = "INSERT INTO lote (nome, evento_id, valor) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, lote.getNome());
			stmt.setInt(2, lote.getEvento().getId()); 
			stmt.setDouble(3, lote.getValor());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Lote> listarTodos() {
		List<Lote> lotes = new ArrayList<>();
		String sql = "SELECT l.id as lote_id, l.nome as lote_nome, l.valor, " +
		             "e.id as evento_id, e.nome as evento_nome " +
					 "FROM lote l JOIN evento e ON l.evento_id = e.id";

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			
			while (rs.next()) {
				Evento evento = new Evento(rs.getInt("evento_id"), rs.getString("evento_nome"));
				Lote lote = new Lote(rs.getInt("lote_id"), rs.getString("lote_nome"), evento, rs.getDouble("valor"));
				lotes.add(lote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lotes;
	}

	public Lote buscarPorId(int id) {
	    String sql = "SELECT l.id as lote_id, l.nome as lote_nome, l.valor, " +
	                 "e.id as evento_id, e.nome as evento_nome " +
	                 "FROM lote l JOIN evento e ON l.evento_id = e.id WHERE l.id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Evento evento = new Evento(rs.getInt("evento_id"), rs.getString("evento_nome"));
				Lote lote = new Lote(rs.getInt("lote_id"), rs.getString("lote_nome"), evento, rs.getDouble("valor"));
				return lote;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}