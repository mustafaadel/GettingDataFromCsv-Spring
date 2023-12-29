package com.atomscode.uploadcsv.student;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCsvRepresentation {
//    @CsvBindByName(column = "firstname")
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
//    @CsvBindByName(column = "lastname")
    private String lastName;
//    @CsvBindByName(column = "age")
    @CsvBindByPosition(position = 2)
    private int age;
}
