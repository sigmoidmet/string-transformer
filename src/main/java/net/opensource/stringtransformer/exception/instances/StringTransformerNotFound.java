package net.opensource.stringtransformer.exception.instances;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StringTransformerNotFound extends ResponseStatusException {

    public StringTransformerNotFound(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
