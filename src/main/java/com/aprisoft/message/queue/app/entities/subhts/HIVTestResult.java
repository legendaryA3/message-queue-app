package com.aprisoft.message.queue.app.entities.subhts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HIVTestResult {
    public String screeningTestResult;
    public String screeningTestDate;
    public String confirmatoryTestResult;
    public String confirmatoryTestDate;
    public String tieBreakerTestResult;
    public String tieBreakerTestDate;
    public String finalResult;
}
