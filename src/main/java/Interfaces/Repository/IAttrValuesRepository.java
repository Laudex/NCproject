package Interfaces.Repository;

import Entity.AttrValues;
import Interfaces.Specification.Specification;

import java.util.List;


public interface IAttrValuesRepository {
    void addAttrValues(AttrValues attrValues);
    void removeAttrValues(AttrValues attrValues);
    void updateAttrValues(AttrValues attrValues);

    List query(Specification specification);
}
