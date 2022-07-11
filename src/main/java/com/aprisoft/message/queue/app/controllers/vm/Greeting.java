package com.aprisoft.message.queue.app.controllers.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Greeting {
    private String msg;
    private String name;
}
