package com.forum.forum.other.specification;

import com.forum.forum.other.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.other.exceptions.EnumNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class SearchSpecification implements Specification<Topic> {

    private String query;
    private String type;
    private Boolean text;

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicatesOr = new ArrayList<>();
        List<Predicate> predicatesAnd = new ArrayList<>();

        if(query != null){
            predicatesOr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+ query.toLowerCase()+"%"));
            predicatesOr.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user").get("username")), "%"+ query.toLowerCase()+"%"));
        }
        if(type != null){
            predicatesAnd.add(criteriaBuilder.equal(root.get("type"), Type.fromValue(type).orElseThrow(() -> new EnumNotFoundException("This type not exist."))));
        }
        if(text != null && text){
            predicatesOr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+ query.toLowerCase()+"%"));
        }
        Predicate predicateOr = criteriaBuilder.or(predicatesOr.toArray(new Predicate[0]));
        Predicate predicateAnd = criteriaBuilder.and(predicatesAnd.toArray(new Predicate[0]));
        return criteriaBuilder.and(predicateOr, predicateAnd);
    }
}
