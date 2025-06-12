package controller;

import model.Lote;
import services.LoteService; 
import java.util.List;

public class LoteController {

	private LoteService loteService = new LoteService();
	
	public void adicionarLote(Lote lote) {
		loteService.adicionarLote(lote);
	}
	
	public List<Lote> listarLotes(){
		return loteService.listarTodos(); 
	}
	
	public Lote buscarLotePorId(int id) {
		return loteService.buscarPorId(id);
	}
}