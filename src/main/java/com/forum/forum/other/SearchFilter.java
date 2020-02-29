package com.forum.forum.other;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SearchFilter implements Specification<Topic> {

    private String query;
    private String type;

    public SearchFilter(String query, String type) {
        this.query = query;
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicatesOr = new ArrayList<>();
        List<Predicate> predicatesAnd = new ArrayList<>();

        if(query != null){
            predicatesOr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+ query.toLowerCase()+"%"));
            predicatesOr.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user").get("username")), "%"+ query.toLowerCase()+"%"));
        }
        if(type != null){
            predicatesAnd.add(criteriaBuilder.equal(root.get("type"), Type.fromValue(type).orElseThrow(() -> new RuntimeException("Type doesn't exist"))));
        }
        Predicate predicateOr = criteriaBuilder.or(predicatesOr.toArray(new Predicate[0]));
        Predicate predicateAnd = criteriaBuilder.and(predicatesAnd.toArray(new Predicate[0]));
        String string = criteriaBuilder.and(predicateOr, predicateAnd).toString();
        return criteriaBuilder.and(predicateOr, predicateAnd);
    }
}
