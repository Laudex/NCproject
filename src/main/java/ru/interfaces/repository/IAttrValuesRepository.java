package ru.interfaces.repository;

import ru.entity.AttrValues;
import ru.interfaces.specification.Specification;

import java.util.List;


public interface IAttrValuesRepository {
    void addAttrValues(AttrValues attrValues);
    void removeAttrValues(AttrValues attrValues);
    void updateAttrValues(AttrValues attrValues);

    List query(Specification specification);
}
