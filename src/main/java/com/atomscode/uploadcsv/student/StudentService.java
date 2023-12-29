package com.atomscode.uploadcsv.student;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public Integer uploadStudent(MultipartFile file) throws IOException {
        Set<Student> students = parseCsv(file);
        // print the students
//        students.forEach(System.out::println);
        studentRepository.saveAll(students);
        return students.size();
    }


    private Set<Student> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            HeaderColumnNameMappingStrategy<StudentCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCsvRepresentation.class); // set type of object to be created
            CsvToBean<StudentCsvRepresentation> csvToBean = new CsvToBeanBuilder<StudentCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
            return csvToBean.parse()
                    .stream() // loop over the list of csv lines
                    .map(csvLine -> Student.builder()
                            .firstName(csvLine.getFirstName())
                            .lastName(csvLine.getLastName())
                            .age(csvLine.getAge())
                            .build())
                    .collect(Collectors.toSet()); // collect the stream and return a set of students
        }

    }
}
