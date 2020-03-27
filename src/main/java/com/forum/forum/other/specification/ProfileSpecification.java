package com.forum.forum.other.specification;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProfileSpecification implements Specification<Topic> {

    private String value;
    private String user;

    public ProfileSpecification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();
        if(value==null){
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.upper(root.join("user").get("username")), user.toUpperCase()));
            return predicate;
        }
        if(value.equals("comment")){
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.upper(root.join("comments").join("user").get("username")), user.toUpperCase()));
            return predicate;
        }
        if(value.equals("vote")){
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.upper(root.join("votes").join("user").get("username")), user.toUpperCase()));
            return predicate;
        }
        throw new RuntimeException("Value not exist");
    }
}
