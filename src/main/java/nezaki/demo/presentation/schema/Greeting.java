package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Greeting {

    @Schema(description = "id description", example = "1", required = true)
    private final long id;

    @Schema(description = "content description", example = "content example", required = true)
    @NotBlank
    @Size(max = 64)
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
