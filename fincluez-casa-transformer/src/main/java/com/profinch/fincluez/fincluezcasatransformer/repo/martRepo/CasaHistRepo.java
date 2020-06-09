package com.profinch.fincluez.fincluezcasatransformer.repo.martRepo;

import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.CasaHist;
import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.CasaHistCK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaHistRepo  extends JpaRepository<CasaHist, CasaHistCK> {
}
