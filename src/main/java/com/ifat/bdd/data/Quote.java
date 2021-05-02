package com.ifat.bdd.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Quote implements Serializable {
    private long id;
    private String text;
    private Status status;
}
