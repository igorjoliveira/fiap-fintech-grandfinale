package br.com.fiap.fintechgrandfinale.domain.interfaces.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;

import java.util.List;

public interface ICarteiraDigitalRepository extends IBaseRepository<CarteiraDigital>{
    List<CarteiraDigital> getAll(int codigoUsuario, int codigoControleFinanceiro, int codigoInstituicaoFinanceira, String nome, String email);
}
