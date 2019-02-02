package wang.congjun.demo07.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
    private Integer id;
    private String name;
    private Book book;

    @Data
    @ToString
    public static class Book{
        private Integer id;
    }
}


