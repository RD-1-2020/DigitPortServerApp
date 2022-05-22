package com.nstu.spdb.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Copy of AbstractPersistable with GenerationType.IDENTITY for entities which need to be incremented by 1.
 * When using PostgreSQL (which has sequences but not identities) Hibernate creates a sequence named
 * <the entity table name>_<id column name>_seq from which it gets identities to the entity
 */
@MappedSuperclass
public abstract class AbstractPersistableIdentity<PK extends Serializable> implements Persistable<PK> {

    private static final long serialVersionUID = -9002592554953655840L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#getId()
     */
    public PK getId() {

        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public void setId(final PK id) {

        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {

        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractPersistableIdentity<?> that = (AbstractPersistableIdentity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}
