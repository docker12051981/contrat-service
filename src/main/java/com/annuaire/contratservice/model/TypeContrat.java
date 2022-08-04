package com.annuaire.contratservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "typecontrat")
public class TypeContrat {
    @Id
    private String id;
    @NotNull
    @Indexed(unique = true)
    private String lib_fr;
    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date lastModifiedTime;
}
