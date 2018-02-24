package pl.erbel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * {
 * "type": "success",
 * "value": {
 * "id": 101,
 * "joke": "Archeologists unearthed an old english dictionary dating back to the year 1236. It defined &quot;victim&quot; as &quot;one who has encountered Chuck Norris&quot;",
 * "categories": []
 * }
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke implements Serializable {

    String type;

    JokeValue value;
}
