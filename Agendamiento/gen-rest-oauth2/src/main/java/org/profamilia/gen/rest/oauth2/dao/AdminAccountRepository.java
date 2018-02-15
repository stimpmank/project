package org.profamilia.gen.rest.oauth2.dao;

import org.profamilia.gen.rest.oauth2.entity.AdminAccount;
import org.springframework.data.repository.CrudRepository;

public interface AdminAccountRepository extends CrudRepository <AdminAccount, Long> {

    public AdminAccount findByAdminName(String adminName);

}
