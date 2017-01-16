package Interfaces.Repository;

import Entity.Attr;
import Interfaces.Specification.AttrSpecification;

import java.util.List;


public interface IAttrRepository {
    void addAttr(Attr attr);
    void removeAttr(Attr attr);
    void updateAttr(Attr attr);

    List query(AttrSpecification specification);
}
