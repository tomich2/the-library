package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.dao.MemberDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import java.util.List;
import javax.inject.Named;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Implementation for member service.
 *
 * @author Tomáš Chomo
 */
@Named
public class MemberServiceImpl extends CrudServiceImpl<Member> implements MemberService {

    @Inject
    MemberDao memberDao;
      
    @Inject
    public MemberServiceImpl(CrudDao<Member> crudDao) {
        super(crudDao);
        this.memberDao = memberDao;
    }
}
