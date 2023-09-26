package com.megazone.bomdi.cms.domain;

import jakarta.persistence.*;
import org.hibernate.envers.*;

@Entity
@RevisionEntity
public class Revision {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long id;
    
    @RevisionTimestamp
    private Long timestamp;
}
