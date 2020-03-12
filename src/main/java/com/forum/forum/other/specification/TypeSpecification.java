package com.forum.forum.other.specification;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class TypeSpecification implements Specification<Topic> {

    private String type;

    public TypeSpecification(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();
        if(type != null){
            Type foundType = Type.fromValue(type).orElseThrow(() -> new RuntimeException("Type doesn't exist"));
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("type"), foundType));
        }
        return predicate;
    }
}
