package com.examples.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement(name="User")
@JsonFilter("siblingFilter")
public class User {
    @JsonProperty("id")
    private Long id;
    private String lastName;
    private String firstName;
    @JsonIgnore
    private LocalDate dateOfBirth;
    @JsonProperty("siblings")
    private Integer siblings;

}
