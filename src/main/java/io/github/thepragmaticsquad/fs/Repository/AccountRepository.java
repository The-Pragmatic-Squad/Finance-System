package io.github.thepragmaticsquad.fs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.thepragmaticsquad.fs.Entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
    
}
