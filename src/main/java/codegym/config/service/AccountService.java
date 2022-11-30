package codegym.config.service;



import codegym.config.model.Account;
import codegym.config.model.Roles;
import codegym.config.repository.IAccountRepo;

import codegym.config.repository.IRolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Autowired
    IRolesRepo iRolesRepo;


    public Account login(String username,String password){
        return iAccountRepo.login(username,password);
    }

    public Account register(Account account){
        return  iAccountRepo.save(account);
    }

    public Roles FindById(Long id){
        return iRolesRepo.findById(id).get();
    }
}