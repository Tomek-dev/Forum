package com.forum.forum.other.specification;

import com.forum.forum.other.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.other.exceptions.EnumNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@AllArgsConstructor
public class TypeSpecification implements Specification<Topic> {

    private String type;

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();
        if(type != null){
            Type foundType = Type.fromValue(type).orElseThrow(() -> new EnumNotFoundException("This type not exist."));
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("type"), foundType));
        }
        return predicate;
    }
}
