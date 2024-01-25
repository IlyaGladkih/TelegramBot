package com.example.demo.telegram;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ChatStatus {

    private boolean today = false;
    private boolean toworrow = false;
    private boolean week = false;
}

