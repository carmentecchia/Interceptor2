package com.example.Interceptor2;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Month {
    private int monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;
}
