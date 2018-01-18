package com.vinson.study.lombok;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

public class ValueTest {

    @Value(staticConstructor="mimi")
    public static class ValueCalss {
        String name;
        @Wither(AccessLevel.PACKAGE)
        @NonFinal
        String age;

        public static ValueCalss mimi() {
            return  new ValueCalss("nnn", "sds");
        }
    }

    public static void main(String[] args) {
        ValueCalss vv = ValueCalss.mimi();
        vv.withAge("sdsd");
    }
}
