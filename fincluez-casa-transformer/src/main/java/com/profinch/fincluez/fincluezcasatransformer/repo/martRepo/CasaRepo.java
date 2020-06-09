package com.profinch.fincluez.fincluezcasatransformer.repo.martRepo;

import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.Casa;
import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.CasaCK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaRepo  extends JpaRepository<Casa, CasaCK> {
}
