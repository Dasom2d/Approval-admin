package com.dasom.task.kakaoPay.repository.user;

import com.dasom.task.kakaoPay.model.user.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private SqlSessionTemplate sqlSessionTemplate;

    public UserRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private static final String PREFIX = UserRepository.class.getCanonicalName() + ".";

    public Optional<User> findById(String id) {
        return sqlSessionTemplate.selectOne(PREFIX + "findById", id);
    }
}
