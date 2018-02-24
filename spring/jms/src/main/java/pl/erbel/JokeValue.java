package pl.erbel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JokeValue implements Serializable{

    Integer id;

    String joke;

    List<String> categories = new ArrayList<>();
}
