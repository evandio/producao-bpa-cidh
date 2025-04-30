package model.services;

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

    public void saveOrUpdadeLote(LoteBpa obj) {
        if (obj.getLoteBpa() == null) {
            int chave = (int) new GeradorChaveService().getProximaChave("tb_lote");
            obj.setLoteBpa(chave);
            dao.inserir(obj);
        } else {
            dao.atualizar(obj);
        }
    }

}
