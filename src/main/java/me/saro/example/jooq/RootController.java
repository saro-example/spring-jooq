package me.saro.example.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONFormat;
import org.jooq.JSONFormat.RecordFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nu.studer.sample.tables.Account;
import nu.studer.sample.tables.AccountRole;

@RestController
public class RootController {
    
    @Autowired
    private DSLContext dsl;
    
    @GetMapping("/")
    public String root() {
        
        Account acccount = Account.ACCOUNT;
        AccountRole role = AccountRole.ACCOUNT_ROLE;
        
        var list = dsl.select().from(acccount).leftJoin(role).on(acccount.NO.eq(role.NO)).fetch();
        
        for (var node : list) {
            System.out.println(node);
            String pw = node.get(acccount.PASSWORD);
            
            System.out.println(pw);
        }
        return list.formatJSON(new JSONFormat().header(false).recordFormat(RecordFormat.OBJECT));
    }
}
