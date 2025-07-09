package model.services;

import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.LoteBpaDao;
import model.entities.LoteBpa;

/**
 *
 * @author evandio.pereira
 */
public class LoteBpaService {

    private LoteBpaDao dao = DaoFactory.createBpaDao();

    public List<LoteBpa> listaTodos() {
        return dao.listaBpa();
    }

    public void saveOrUpdateLote(LoteBpa obj) {
        if (obj.getLoteBpa() == null) {
            int chave = (int) new GeradorChaveService().getProximaChave("tb_lote");
            System.out.println("Nova chave gerada: " + chave);
            obj.setLoteBpa(chave);
            dao.inserir(obj);
        } else {
            System.out.println("Atualizado o Lote!");
            dao.atualizar(obj);
        }
    }

    public boolean existsLoteBpa(Integer isnProfissional, Date dataAtendimento, int turno, Integer nrLote) {
        return dao.existsLoteBpa(isnProfissional, dataAtendimento, turno, nrLote);
    }
}
