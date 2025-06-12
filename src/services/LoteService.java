package services;

import java.util.List;
import model.Lote;
import repository.LoteRepository;

public class LoteService {

    private LoteRepository loteRepository = new LoteRepository();

    public void adicionarLote(Lote lote) {
        loteRepository.salvar(lote);
    }

    public List<Lote> listarTodos() {
        return loteRepository.listarTodos();
    }

    public Lote buscarPorId(int id) {
        return loteRepository.buscarPorId(id);
    }
}