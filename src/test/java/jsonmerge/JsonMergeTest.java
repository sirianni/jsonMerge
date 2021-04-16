package jsonmerge;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JsonMergeTest {

  @Test
  public void test() throws Exception {
    var mapper = new ObjectMapper();
    var node = mapper.readTree("{\"root\":{\"a\":\"aaa\",\"foo\":\"original\"}}");
    mapper.readerForUpdating(node).readTree("{\"root\":{\"b\":\"bbb\",\"foo\":\"updated\"}}");

    assertThat(node.get("root").path("a").asText()).isEqualTo("aaa");
    assertThat(node.get("root").path("b").asText()).isEqualTo("bbb");
    assertThat(node.get("root").path("foo").asText()).isEqualTo("updated");
  }

}
