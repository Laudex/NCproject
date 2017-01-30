package ru.interfaces.repository;

import ru.entity.Attr;
import ru.interfaces.specification.Specification;

import java.util.List;


public interface IAttrRepository {
    void addAttr(Attr attr);
    void removeAttr(Attr attr);
    void updateAttr(Attr attr);

    List query(Specification specification);
}
