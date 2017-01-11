package Interfaces.Repository;

import Entity.AttrValues;
import Interfaces.Specification.AttrValuesSpecification;

import java.util.List;


public interface AttrValuesRepository {
    void addAttrValues(AttrValues attrValues);
    void removeAttrValues(AttrValues attrValues);
    void updateAttrValues(AttrValues attrValues);

    List query(AttrValuesSpecification specification);
}
